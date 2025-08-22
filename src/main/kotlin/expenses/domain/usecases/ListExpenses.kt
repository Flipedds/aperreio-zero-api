package com.flipedds.expenses.domain.usecases

import com.flipedds.expenses.domain.controllers.ExpenseController
import com.flipedds.expenses.domain.entities.Expense

suspend fun ExpenseController.listExpenses(): List<Expense> {
    return expenseRepository.dbQuery { expenseRepository.list() }
}
