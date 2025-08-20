package com.flipedds

import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import com.flipedds.expenses.adapters.out.rest.expensesRoutes
import com.flipedds.infra.*
import io.ktor.server.engine.embeddedServer
import org.koin.ktor.ext.inject

fun main() {
    embeddedServer(
        factory = ApplicationConfig.ENGINE,
        port = ApplicationConfig.PORT,
        host = ApplicationConfig.HOST) {
        configureFrameworks()
        configureDatabases()
        val expenseRepository by inject<IExpenseRepository>()
        configureSecurity()
        configureHTTP()
        configureRouting {
            expensesRoutes(expenseRepository)
        }
    }.start(wait = true)
}
