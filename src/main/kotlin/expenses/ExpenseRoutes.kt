package com.flipedds.expenses

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Routing.expensesRoutes(expenseService: IExpenseService) {
    route("/expenses") {
        post {
            val expense = call.receive<ExpenseRequest>()
            val id = expenseService.dbQuery { expenseService.create(expense) }
            call.respond(HttpStatusCode.Created, id)
        }

        get {
            val expenses = expenseService.dbQuery { expenseService.list() }
            call.respond(HttpStatusCode.OK, expenses)
        }

        get("/total") {
            val total = expenseService.dbQuery { expenseService.list().sumOf { it.amount } }
            call.respond(HttpStatusCode.OK, mapOf("total" to total))
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                return@get
            }
            val expense = expenseService.dbQuery { expenseService.read(id) }

            if (expense.isPresent) {
                call.respond(HttpStatusCode.OK, expense.get())
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toInt()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                return@put
            }
            val expense = call.receive<Expense>()
            expenseService.dbQuery { expenseService.update(id, expense) }
            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                return@delete
            }
            expenseService.dbQuery { expenseService.delete(id) }
            call.respond(HttpStatusCode.OK)
        }
    }
}