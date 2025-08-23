package com.flipedds

import com.flipedds.expenses.domain.controllers.ExpenseController
import com.flipedds.expenses.adapters.out.routes.expensesRoutes
import com.flipedds.ktor.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module(){
    configureFrameworks()
    configureDatabases()
    configureSecurity()
    configureHTTP()
    configureMetrics()

    // controllers
    val expenseController by inject<ExpenseController>()

    configureRouting {
        expensesRoutes(expenseController)
    }
}