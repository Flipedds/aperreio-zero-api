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
    authentication {
        jwt {
            realm = ApplicationConfig.Jwt.REALM
            verifier(
                JWT
                    .require(Algorithm.HMAC256(ApplicationConfig.Jwt.SECRET))
                    .withAudience(ApplicationConfig.Jwt.AUDIENCE)
                    .withIssuer(ApplicationConfig.Jwt.ISSUER)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience
                    .contains(ApplicationConfig.Jwt.AUDIENCE)) JWTPrincipal(credential.payload) else null
            }
        }
    }
    routing {
        route("/auth") {
            post {
                val token = JWT.create()
                    .withAudience(ApplicationConfig.Jwt.AUDIENCE)
                    .withIssuer(ApplicationConfig.Jwt.ISSUER)
                    .withClaim("username", "")
                    .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                    .sign(Algorithm.HMAC256(ApplicationConfig.Jwt.SECRET))

                call.respond(HttpStatusCode.OK, hashMapOf("token" to token))
            }
        }
    }
}
