package com.flipedds.infra

import com.flipedds.expenses.adapters.`in`.repositories.ExpenseRepository
import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
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
            singleOf(::ExpenseRepository) { bind<IExpenseRepository>() }
        })
    }
}
