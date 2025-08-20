package com.flipedds.expenses.usecases

import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import com.flipedds.expenses.domain.Expense
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import java.util.*

suspend fun Application.getExpenseById(id: Int): Optional<Expense> {
    val expenseRepository by inject<IExpenseRepository>()
    return expenseRepository.dbQuery { expenseRepository.read(id) }
}