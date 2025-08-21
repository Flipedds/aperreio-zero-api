package com.flipedds

import com.flipedds.expenses.adapters.out.rest.expensesRoutes
import com.flipedds.infra.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module(){
    configureFrameworks()
    configureDatabases()
    configureSecurity()
    configureHTTP()
    configureRouting {
        expensesRoutes(this@module)
    }
}