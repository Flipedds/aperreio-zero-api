package com.flipedds.ktor

import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.plugins.origin

val RequestLoggingPlugin = createApplicationPlugin(name = "RequestLoggingPlugin") {
    onCall { call ->
        call.request.origin.apply {
            println("[$method] -> Request URL: $scheme://$localHost:$localPort$uri")
        }
    }
}