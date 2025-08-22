package com.flipedds.expenses.domain.usecases

import com.flipedds.expenses.domain.controllers.ExpenseController
import com.flipedds.expenses.domain.entities.Expense

suspend fun ExpenseController.createExpense(expense: Expense): Int {
    val id = expenseRepository.dbQuery { expenseRepository.create(expense) }
    return id
}

