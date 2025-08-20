package com.flipedds.infra

import com.flipedds.expenses.adapters.`in`.tables.Expenses
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(ApplicationConfig.DB_URL, "org.sqlite.JDBC")
    transaction(database) {
        SchemaUtils.create(Expenses)
    }
}
