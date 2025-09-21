package com.github.dizk.almanakk

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
        @RequestBody connectionForm: ConnectionForm,
    ): ResponseEntity<ConnectionTestResponse> {
        val validationResult = connectionService.validateConnectionString(connectionForm.jdbcUrl)

        if (!validationResult.valid) {
            return ResponseEntity.ok(
                ConnectionTestResponse(
                    success = false,
                    message = validationResult.message,
                ),
            )
        }

        val config = parseJdbcUrl(connectionForm.jdbcUrl)
        val testResult = connectionService.testConnection(config)

        return ResponseEntity.ok(
            ConnectionTestResponse(
                success = testResult.success,
                message = testResult.message,
                databaseVersion = testResult.databaseVersion,
                driverVersion = testResult.driverVersion,
            ),
        )
    }

    @PostMapping("/start-exploring")
    fun startExploring(
        @RequestBody connectionForm: ConnectionForm,
        model: Model,
    ): String {
        val config = parseJdbcUrl(connectionForm.jdbcUrl)
        return "redirect:/tables"
    }

    private fun parseJdbcUrl(jdbcUrl: String): ConnectionConfig {
        val regex = Regex("""^jdbc:postgresql://([^:/]+)(?::(\d+))?/([^?]+)(?:\?(.*))?$""")
        val match =
            regex.find(jdbcUrl)
                ?: throw IllegalArgumentException("Invalid JDBC URL format")

        val host = match.groupValues[1]
        val port = match.groupValues[2].toIntOrNull() ?: 5432
        val database = match.groupValues[3]
        val params = match.groupValues[4]

        var username = ""
        var password = ""
        var schema: String? = null
        var sslMode = "require"

        if (params.isNotEmpty()) {
            params.split("&").forEach { param ->
                val (key, value) = param.split("=", limit = 2)
                when (key) {
                    "user" -> username = value
                    "password" -> password = value
                    "currentSchema" -> schema = value
                    "sslmode" -> sslMode = value
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
)

data class ConnectionTestResponse(
    val success: Boolean,
    val message: String,
    val databaseVersion: String? = null,
    val driverVersion: String? = null,
)
