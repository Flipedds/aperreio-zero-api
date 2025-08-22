package com.flipedds.expenses.domain.usecases

import com.flipedds.expenses.domain.controllers.ExpenseController
import com.flipedds.expenses.domain.entities.Expense

suspend fun ExpenseController.updateExpense(id: Int, expense: Expense) {
    expenseRepository.dbQuery { expenseRepository.update(id, expense) }
}
