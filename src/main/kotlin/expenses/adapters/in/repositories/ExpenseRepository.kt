package com.flipedds.expenses.adapters.`in`.repositories

import com.flipedds.expenses.adapters.`in`.tables.Expenses
import com.flipedds.expenses.domain.entities.Expense
import com.flipedds.expenses.domain.interfaces.IExpenseRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import java.util.Optional

class ExpenseRepository: IExpenseRepository {
    override fun create(expense: Expense): Int =
        Expenses.insertAndGetId {
            it[description] = expense.description
            it[amount] = expense.amount
            it[date] = expense.date
            it[type] = expense.type
        }.value

    override fun list(): List<Expense> =
        Expenses.selectAll().map { Expense(
            it[Expenses.id].value,
            it[Expenses.description],
            it[Expenses.amount],
            it[Expenses.date],
            it[Expenses.type]) }

    override fun read(id: Int): Optional<Expense> =
            Expenses.selectAll()
                .where { Expenses.id eq id }
                .map { Expense(
                    it[Expenses.id].value,
                    it[Expenses.description],
                    it[Expenses.amount],
                    it[Expenses.date],
                    it[Expenses.type]) }
                .singleOrNull().let { Optional.ofNullable(it) }
    override fun update(id: Int, expense: Expense) =
            Expenses.update({ Expenses.id eq id }) {
                it[description] = expense.description
                it[amount] = expense.amount
                it[type] = expense.type
            }
    override fun delete(id: Int) = Expenses.deleteWhere { Expenses.id eq(id) }

    override suspend fun <T> dbQuery(block: suspend () -> T): T =
            newSuspendedTransaction(Dispatchers.IO) { block() }
}
