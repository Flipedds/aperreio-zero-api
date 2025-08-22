package com.flipedds.expenses.domain.usecases

import com.flipedds.expenses.domain.controllers.ExpenseController
import com.flipedds.expenses.domain.entities.Expense
import java.util.*

suspend fun ExpenseController.getExpenseById(id: Int): Optional<Expense> {
    return expenseRepository.dbQuery { expenseRepository.read(id) }
}