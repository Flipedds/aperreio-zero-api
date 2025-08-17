package com.flipedds

import com.flipedds.config.configureDatabases
import com.flipedds.config.configureFrameworks
import com.flipedds.config.configureHTTP
import com.flipedds.config.configureRouting
import com.flipedds.config.configureSecurity
import com.flipedds.expenses.IExpenseService
import com.flipedds.expenses.expensesRoutes
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.inject

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0"){
        configureFrameworks()
        configureDatabases()
        val expenseService by inject<IExpenseService>()
        configureSecurity()
        configureHTTP()
        configureRouting {
            expensesRoutes(expenseService)
        }
    }.start(wait = true)
}
