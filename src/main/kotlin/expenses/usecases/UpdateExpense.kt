package com.flipedds.expenses.usecases

import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import com.flipedds.expenses.domain.Expense
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

suspend fun Application.updateExpense(id: Int, expense: Expense) {
    val expenseRepository by inject<IExpenseRepository>()
    expenseRepository.dbQuery { expenseRepository.update(id, expense) }
}
