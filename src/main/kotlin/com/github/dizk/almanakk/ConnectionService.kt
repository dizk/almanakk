package com.github.dizk.almanakk

import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import javax.sql.DataSource

@Service
class ConnectionService {
    fun createDataSource(config: ConnectionConfig): DataSource =
        DriverManagerDataSource().apply {
            setDriverClassName("org.postgresql.Driver")
            url = config.toJdbcUrl()
            username = config.username
            password = config.password
            setConnectionProperties(config.toProperties())
        }

    fun testConnection(config: ConnectionConfig): ConnectionTestResult {
        val jdbcUrl = config.toJdbcUrl()
        val properties = config.toProperties()

        return try {
            DriverManager.setLoginTimeout(config.connectionTimeout / 1000)

            DriverManager.getConnection(jdbcUrl, properties).use { connection ->
                enforceReadOnly(connection)
                verifySelectPermissions(connection)

                ConnectionTestResult(
                    success = true,
                    message = "Connection successful",
                    databaseVersion = connection.metaData.databaseProductVersion,
                    driverVersion = connection.metaData.driverVersion,
                )
            }
        } catch (e: SQLException) {
            when (e.sqlState) {
                "08001", "08003", "08004", "08006" ->
                    ConnectionTestResult(
                        success = false,
                        message = "Cannot connect to database: ${e.message}",
                        errorCode = e.sqlState,
                    )
                "28000", "28P01" ->
                    ConnectionTestResult(
                        success = false,
                        message = "Authentication failed: Invalid username or password",
                        errorCode = e.sqlState,
                    )
                "42501" ->
                    ConnectionTestResult(
                        success = false,
                        message = "Permission denied: User lacks SELECT privileges",
                        errorCode = e.sqlState,
                    )
                else ->
                    ConnectionTestResult(
                        success = false,
                        message = "Database error: ${e.message}",
                        errorCode = e.sqlState,
                    )
            }
        } catch (e: Exception) {
            ConnectionTestResult(
                success = false,
                message = "Unexpected error: ${e.message}",
                errorCode = null,
            )
        }
    }

    fun validateConnectionString(connectionString: String): ValidationResult {
        val regex =
            Regex(
                """^jdbc:postgresql://([^:/]+)(:\d+)?/([^?]+)(\?.*)?$""",
            )

        return if (regex.matches(connectionString)) {
            ValidationResult(valid = true, message = "Valid PostgreSQL connection string")
        } else {
            ValidationResult(
                valid = false,
                message = "Invalid connection string format. Expected: jdbc:postgresql://host:port/database",
            )
        }
    }

    fun establishConnection(config: ConnectionConfig): Connection {
        val jdbcUrl = config.toJdbcUrl()
        val properties = config.toProperties()

        DriverManager.setLoginTimeout(config.connectionTimeout / 1000)
        val connection = DriverManager.getConnection(jdbcUrl, properties)

        try {
            enforceReadOnly(connection)
            verifySelectPermissions(connection)
        } catch (e: Exception) {
            connection.close()
            throw e
        }

        return connection
    }

    private fun enforceReadOnly(connection: Connection) {
        connection.isReadOnly = true
        connection.createStatement().use { statement ->
            statement.execute("SET SESSION CHARACTERISTICS AS TRANSACTION READ ONLY")
        }
    }

    private fun verifySelectPermissions(connection: Connection) {
        connection.createStatement().use { statement ->
            statement.executeQuery("SELECT 1").use { rs ->
                if (!rs.next()) {
                    throw SQLException("Cannot verify database access", "42501")
                }
            }
        }
    }
}

data class ConnectionTestResult(
    val success: Boolean,
    val message: String,
    val databaseVersion: String? = null,
    val driverVersion: String? = null,
    val errorCode: String? = null,
)

data class ValidationResult(
    val valid: Boolean,
    val message: String,
)
