package com.flipedds.expenses.usecases

import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import com.flipedds.expenses.domain.Expense
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

suspend fun Application.createExpense(expense: Expense): Int {
    val expenseRepository by inject<IExpenseRepository>()
    val id = expenseRepository.dbQuery { expenseRepository.create(expense) }

    return id
}

