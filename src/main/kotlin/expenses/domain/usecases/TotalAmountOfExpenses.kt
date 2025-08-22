package com.flipedds.expenses.domain.usecases

import com.flipedds.expenses.domain.controllers.ExpenseController

suspend fun ExpenseController.totalAmountOfExpenses(): Double {
    return expenseRepository.dbQuery {
        expenseRepository.list().sumOf { it.amount }
    }
}
