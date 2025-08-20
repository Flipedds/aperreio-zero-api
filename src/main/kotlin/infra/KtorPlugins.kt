package com.flipedds.infra

import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.plugins.origin

val RequestLoggingPlugin = createApplicationPlugin(name = "RequestLoggingPlugin") {
    onCall { call ->
        call.request.origin.apply {
            println("Request URL: $scheme://$localHost:$localPort$uri")
        }
    }
}