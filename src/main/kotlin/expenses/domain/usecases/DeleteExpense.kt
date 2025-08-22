package com.flipedds.expenses.domain.usecases

import com.flipedds.expenses.domain.controllers.ExpenseController

suspend fun ExpenseController.deleteExpense(id: Int) {
    expenseRepository.dbQuery { expenseRepository.delete(id) }
}

