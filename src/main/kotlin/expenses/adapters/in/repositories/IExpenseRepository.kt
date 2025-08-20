package com.flipedds.expenses.adapters.`in`.repositories

import com.flipedds.expenses.domain.Expense
import java.util.Optional

interface IExpenseRepository {
    fun create(expense: Expense): Int
    fun list(): List<Expense>
    fun read(id: Int): Optional<Expense>
    fun update(id: Int, expense: Expense): Int
    fun delete(id: Int): Int
    suspend fun <T> dbQuery(block: suspend () -> T): T
}