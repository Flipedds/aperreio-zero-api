package com.flipedds.ktor

import com.flipedds.expenses.adapters.`in`.tables.Expenses
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val url = getStringOrDefault("storage.jdbcURL", "jdbc:sqlite:./src/main/kotlin/db/app.db")

    val driver = getStringOrDefault("storage.driverClassName", "org.sqlite.JDBC")

    val user = getStringOrDefault("storage.user","")

    val pass = getStringOrDefault("storage.password", "")

    val database = Database.connect(url, driver, user, pass)

    transaction(database) {
        SchemaUtils.create(Expenses)
    }
}
