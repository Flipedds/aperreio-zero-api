package com.flipedds.infra

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureSecurity() {
    val secret = getStringOrDefault("jwt.secret", "secret")
    val realMe = getStringOrDefault("jwt.realm", "ktor aperreio zero")
    val issuer = getStringOrDefault("jwt.issuer", "https://jwt-provider-domain/")
    val audience = getStringOrDefault("jwt.audience", "jwt-audience")

    authentication {
        jwt {
            realm = realMe
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience
                    .contains(audience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
    routing {
        route("/auth") {
            post {
                val token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withClaim("username", "")
                    .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                    .sign(Algorithm.HMAC256(secret))

                call.respond(HttpStatusCode.OK, hashMapOf("token" to token))
            }
        }
    }
}
