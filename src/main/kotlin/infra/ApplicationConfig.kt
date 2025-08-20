package com.flipedds.infra

import io.ktor.server.netty.*

object ApplicationConfig {
    const val HOST = "localhost"
    const val PORT = 8080
    const val DB_URL = "jdbc:sqlite:./db/app.db"
    val ENGINE = Netty

    object Jwt {
        const val AUDIENCE = "jwt-audience"
        const val ISSUER = "https://jwt-provider-domain/"
        const val REALM = "ktor sample app"
        const val SECRET = "secret"
    }
}
