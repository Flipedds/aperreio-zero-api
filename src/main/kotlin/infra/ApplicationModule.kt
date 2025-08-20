package com.flipedds.infra

import com.flipedds.expenses.adapters.out.rest.expensesRoutes
import io.ktor.server.application.*

fun Application.module(){
    configureFrameworks()
    configureDatabases()
    configureSecurity()
        configureDatabases()
    configureHTTP()
    configureRouting {
        expensesRoutes(this@module)
    }
}