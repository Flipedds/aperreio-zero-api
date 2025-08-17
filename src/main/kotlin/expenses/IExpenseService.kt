package com.flipedds.expenses

import java.util.Optional

interface IExpenseService {
    fun create(expense: ExpenseRequest): Int
    fun list(): List<Expense>
    fun read(id: Int): Optional<Expense>
    fun update(id: Int, expense: Expense): Int
    fun delete(id: Int): Int
    suspend fun <T> dbQuery(block: suspend () -> T): T
}