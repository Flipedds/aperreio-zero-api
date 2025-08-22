package com.flipedds.ktor

import io.ktor.server.application.*

fun Application.getStringOrDefault(path: String, default: String): String {
    return environment
        .config.propertyOrNull(path)?.getString()
        ?: default
}
