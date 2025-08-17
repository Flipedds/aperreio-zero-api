package com.flipedds.expenses

import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import java.time.format.DateTimeFormatter
import java.util.Optional

class ExpenseService: IExpenseService {
    object Expenses : IntIdTable() {
        val description = varchar("description", 255)
        val amount = double("amount")
        val date = datetime("date")
        val type = enumeration<Type>("type")
    }

    override fun create(expense: ExpenseRequest): Int =
        Expenses.insertAndGetId {
            it[description] = expense.description
            it[amount] = expense.amount
            it[date] = LocalDateTime.parse(
                java.time.LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
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
                it[date] = expense.date
                it[type] = expense.type
            }
    override fun delete(id: Int) = Expenses.deleteWhere { Expenses.id eq(id) }

    override suspend fun <T> dbQuery(block: suspend () -> T): T =
            newSuspendedTransaction(Dispatchers.IO) { block() }
}
