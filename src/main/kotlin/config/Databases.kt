package com.flipedds.config

import com.flipedds.expenses.ExpenseService
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect("jdbc:sqlite:./db/app.db", "org.sqlite.JDBC")
    transaction(database) {
        SchemaUtils.create(ExpenseService.Expenses)
    }
}
