package com.flipedds.config

import com.flipedds.expenses.ExpenseService
import com.flipedds.expenses.IExpenseService
import io.ktor.server.application.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            singleOf(::ExpenseService) { bind<IExpenseService>() }
        })
    }
}
