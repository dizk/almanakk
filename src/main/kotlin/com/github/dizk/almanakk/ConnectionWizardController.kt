package com.github.dizk.almanakk

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ConnectionWizardController(
    private val connectionService: ConnectionService,
) {
    @GetMapping("/")
    fun showConnectionWizard(model: Model): String {
        model.addAttribute("connectionForm", ConnectionForm())
        return "connection-wizard"
    }

    @PostMapping("/test-connection")
    fun testConnection(
        connectionForm: ConnectionForm,
        model: Model,
    ): String {
        try {
            val validationResult = connectionService.validateConnectionString(connectionForm.jdbcUrl)

            if (!validationResult.valid) {
                model.addAttribute("alertType", "danger")
                model.addAttribute("alertMessage", "Connection failed")
                model.addAttribute("alertDetails", validationResult.message)
                return "fragments/alerts :: danger(message=${model.getAttribute(
                    "alertMessage",
                )}, details=${model.getAttribute("alertDetails")})"
            }

            val config = parseJdbcUrl(connectionForm.jdbcUrl, connectionForm.username, connectionForm.password)
            val testResult = connectionService.testConnection(config)

            if (testResult.success) {
                val details =
                    buildString {
                        append(testResult.message)
                        testResult.databaseVersion?.let { append("<br><small>Database: $it</small>") }
                    }
                model.addAttribute("alertMessage", "Connection successful!")
                model.addAttribute("alertDetails", details)
                return "fragments/alerts :: success(message=${model.getAttribute(
                    "alertMessage",
                )}, details=${model.getAttribute("alertDetails")})"
            } else {
                model.addAttribute("alertMessage", "Connection failed")
                model.addAttribute("alertDetails", testResult.message)
                return "fragments/alerts :: danger(message=${model.getAttribute(
                    "alertMessage",
                )}, details=${model.getAttribute("alertDetails")})"
            }
        } catch (e: Exception) {
            model.addAttribute("alertMessage", "Connection failed")
            model.addAttribute("alertDetails", "Error parsing connection URL: ${e.message}")
            return "fragments/alerts :: danger(message=${model.getAttribute(
                "alertMessage",
            )}, details=${model.getAttribute("alertDetails")})"
        }
    }

    @PostMapping("/start-exploring")
    fun startExploring(
        connectionForm: ConnectionForm,
        model: Model,
    ): String {
        return try {
            val validationResult = connectionService.validateConnectionString(connectionForm.jdbcUrl)
            if (!validationResult.valid) {
                model.addAttribute("error", validationResult.message)
                model.addAttribute("connectionForm", connectionForm)
                return "connection-wizard"
            }

            val config = parseJdbcUrl(connectionForm.jdbcUrl, connectionForm.username, connectionForm.password)
            val testResult = connectionService.testConnection(config)

            if (!testResult.success) {
                model.addAttribute("error", testResult.message)
                model.addAttribute("connectionForm", connectionForm)
                return "connection-wizard"
            }

            "redirect:/tables"
        } catch (e: Exception) {
            model.addAttribute("error", "Error: ${e.message}")
            model.addAttribute("connectionForm", connectionForm)
            "connection-wizard"
        }
    }

    private fun parseJdbcUrl(
        jdbcUrl: String,
        username: String,
        password: String,
    ): ConnectionConfig {
        val parsed = parseJdbcUrlString(jdbcUrl)
        return buildConnectionConfig(parsed, username, password)
    }

    private fun parseJdbcUrlString(jdbcUrl: String): ParsedJdbcUrl {
        val postgresRegex = Regex("""^jdbc:postgresql://([^:/]+)(?::(\d+))?/([^?]+)(?:\?(.*))?$""")
        val mysqlRegex = Regex("""^jdbc:mysql://([^:/]+)(?::(\d+))?/([^?]+)(?:\?(.*))?$""")

        val (match, dbType, defaultPort) =
            when {
                postgresRegex.matches(jdbcUrl) -> Triple(postgresRegex.find(jdbcUrl), DatabaseType.POSTGRESQL, 5432)
                mysqlRegex.matches(jdbcUrl) -> Triple(mysqlRegex.find(jdbcUrl), DatabaseType.MYSQL, 3306)
                else -> throw IllegalArgumentException("Unsupported JDBC URL format. Only PostgreSQL and MySQL are supported.")
            }

        match ?: throw IllegalArgumentException("Invalid JDBC URL format")

        return ParsedJdbcUrl(
            host = match.groupValues[1],
            port = match.groupValues[2].toIntOrNull() ?: defaultPort,
            database = match.groupValues[3],
            params = parseQueryParams(match.groupValues[4]),
            databaseType = dbType,
        )
    }

    private fun parseQueryParams(queryString: String): Map<String, String> {
        if (queryString.isEmpty()) return emptyMap()

        return queryString
            .split("&")
            .mapNotNull { param ->
                val parts = param.split("=", limit = 2)
                if (parts.size == 2) parts[0] to parts[1] else null
            }.toMap()
    }

    private fun buildConnectionConfig(
        parsed: ParsedJdbcUrl,
        username: String,
        password: String,
    ): ConnectionConfig =
        ConnectionConfig(
            host = parsed.host,
            port = parsed.port,
            database = parsed.database,
            username = username,
            password = password,
            schema = parsed.params["currentSchema"],
            sslMode = parsed.params["sslmode"] ?: "require",
        )
}

data class ParsedJdbcUrl(
    val host: String,
    val port: Int,
    val database: String,
    val params: Map<String, String>,
    val databaseType: DatabaseType,
)

enum class DatabaseType {
    POSTGRESQL,
    MYSQL,
}

data class ConnectionForm(
    val jdbcUrl: String = "",
    val username: String = "",
    val password: String = "",
)
