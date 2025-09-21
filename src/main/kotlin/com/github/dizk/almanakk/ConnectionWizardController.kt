package com.github.dizk.almanakk

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

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
    @ResponseBody
    fun testConnection(
        connectionForm: ConnectionForm,
    ): String {
        return try {
            val validationResult = connectionService.validateConnectionString(connectionForm.jdbcUrl)

            if (!validationResult.valid) {
                return """
                    <div class="alert alert-danger" role="alert">
                        <div class="d-flex align-items-center">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
                                <use xlink:href="#exclamation-triangle-fill"/>
                            </svg>
                            <div>
                                <strong>Connection failed</strong><br>
                                ${validationResult.message}
                            </div>
                        </div>
                    </div>
                """.trimIndent()
            }

            val config = parseJdbcUrl(connectionForm.jdbcUrl, connectionForm.username, connectionForm.password)
            val testResult = connectionService.testConnection(config)

            if (testResult.success) {
                """
                    <div class="alert alert-success" role="alert">
                        <div class="d-flex align-items-center">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:">
                                <use xlink:href="#check-circle-fill"/>
                            </svg>
                            <div>
                                <strong>Connection successful!</strong><br>
                                ${testResult.message}
                                ${testResult.databaseVersion?.let { "<br><small>Database: $it</small>" } ?: ""}
                            </div>
                        </div>
                    </div>
                """.trimIndent()
            } else {
                """
                    <div class="alert alert-danger" role="alert">
                        <div class="d-flex align-items-center">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
                                <use xlink:href="#exclamation-triangle-fill"/>
                            </svg>
                            <div>
                                <strong>Connection failed</strong><br>
                                ${testResult.message}
                            </div>
                        </div>
                    </div>
                """.trimIndent()
            }
        } catch (e: Exception) {
            """
                <div class="alert alert-danger" role="alert">
                    <div class="d-flex align-items-center">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
                            <use xlink:href="#exclamation-triangle-fill"/>
                        </svg>
                        <div>
                            <strong>Connection failed</strong><br>
                            Error parsing connection URL: ${e.message}
                        </div>
                    </div>
                </div>
            """.trimIndent()
        }
    }

    @PostMapping("/start-exploring")
    fun startExploring(
        connectionForm: ConnectionForm,
        model: Model,
    ): String {
        val config = parseJdbcUrl(connectionForm.jdbcUrl, connectionForm.username, connectionForm.password)
        return "redirect:/tables"
    }

    private fun parseJdbcUrl(jdbcUrl: String, username: String, password: String): ConnectionConfig {
        val regex = Regex("""^jdbc:postgresql://([^:/]+)(?::(\d+))?/([^?]+)(?:\?(.*))?$""")
        val match =
            regex.find(jdbcUrl)
                ?: throw IllegalArgumentException("Invalid JDBC URL format")

        val host = match.groupValues[1]
        val port = match.groupValues[2].toIntOrNull() ?: 5432
        val database = match.groupValues[3]
        val params = match.groupValues[4]

        var schema: String? = null
        var sslMode = "disable"

        if (params.isNotEmpty()) {
            params.split("&").forEach { param ->
                val parts = param.split("=", limit = 2)
                if (parts.size == 2) {
                    val (key, value) = parts
                    when (key) {
                        "currentSchema" -> schema = value
                        "sslmode" -> sslMode = value
                    }
                }
            }
        }

        return ConnectionConfig(
            host = host,
            port = port,
            database = database,
            username = username,
            password = password,
            schema = schema,
            sslMode = sslMode,
        )
    }
}

data class ConnectionForm(
    var jdbcUrl: String = "",
    var username: String = "",
    var password: String = "",
)
