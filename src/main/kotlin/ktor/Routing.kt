package com.flipedds.ktor

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(routes: Routing.() -> Unit) {
    routing {
        routes()
    }
}
