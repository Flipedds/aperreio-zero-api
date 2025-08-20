package com.flipedds.expenses.usecases

import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

suspend fun Application.totalAmountOfExpenses(): Double {
    val expenseRepository by inject<IExpenseRepository>()
    return expenseRepository.dbQuery {
        expenseRepository.list().sumOf { it.amount }
    }
}
