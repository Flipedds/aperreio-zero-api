package com.flipedds.expenses.domain.interfaces

import com.flipedds.expenses.domain.entities.Expense
import java.util.Optional

interface IExpenseRepository {
    fun create(expense: Expense): Int
    fun list(): List<Expense>
    fun read(id: Int): Optional<Expense>
    fun update(id: Int, expense: Expense): Int
    fun delete(id: Int): Int
    suspend fun <T> dbQuery(block: suspend () -> T): T
}