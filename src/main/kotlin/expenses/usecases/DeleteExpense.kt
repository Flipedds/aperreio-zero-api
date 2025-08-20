package com.flipedds.expenses.usecases

import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

suspend fun Application.deleteExpense(id: Int) {
    val expenseRepository by inject<IExpenseRepository>()
    expenseRepository.dbQuery { expenseRepository.delete(id) }
}

