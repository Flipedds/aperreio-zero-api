package com.flipedds

import com.flipedds.infra.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer

fun main() {
    embeddedServer(
        factory = ApplicationConfig.ENGINE,
        port = ApplicationConfig.PORT,
        host = ApplicationConfig.HOST,
        module = Application::module)
        .start(wait = true)
}
