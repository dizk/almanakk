package com.github.dizk.almanakk

data class ConnectionConfig(
    val host: String,
    val port: Int,
    val database: String,
    val username: String,
    val password: String,
    val schema: String? = null,
    val sslMode: String = "disable",
    val connectionTimeout: Int = 5000,
    val readOnly: Boolean = true,
) {
    fun toJdbcUrl(): String {
        val baseUrl = "jdbc:postgresql://$host:$port/$database"
        val params = mutableListOf<String>()

        schema?.let { params.add("currentSchema=$it") }
        params.add("sslmode=$sslMode")
        params.add("connectTimeout=${connectionTimeout / 1000}")

        return if (params.isNotEmpty()) {
            "$baseUrl?${params.joinToString("&")}"
        } else {
            baseUrl
        }
    }

    fun toProperties(): java.util.Properties =
        java.util.Properties().apply {
            setProperty("readOnly", readOnly.toString())
        }
}
