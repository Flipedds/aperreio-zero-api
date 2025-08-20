package com.flipedds.expenses.adapters.out.rest

import com.flipedds.expenses.adapters.out.dtos.ExpenseDto
import com.flipedds.expenses.adapters.`in`.repositories.IExpenseRepository
import com.flipedds.expenses.adapters.out.converters.toDto
import com.flipedds.expenses.adapters.out.converters.toEntity
import com.flipedds.expenses.adapters.out.converters.toListDto
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Routing.expensesRoutes(expenseRepository: IExpenseRepository) {
    route("/expenses") {
        authenticate {
            post {
                val expense = call.receive<ExpenseDto>().toEntity()
                val id = expenseRepository.dbQuery { expenseRepository.create(expense) }
                call.respond(HttpStatusCode.Created, id to "Expense created successfully !")
            }
            put("/{id}") {
                val id = call.parameters["id"]?.toInt()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                    return@put
                }
                val expense = call.receive<ExpenseDto>().toEntity()
                expenseRepository.dbQuery { expenseRepository.update(id, expense) }
                call.respond(HttpStatusCode.OK)
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toInt()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                    return@delete
                }
                expenseRepository.dbQuery { expenseRepository.delete(id) }
                call.respond(HttpStatusCode.OK)
            }
        }

        get {
            val expenses = expenseRepository.dbQuery { expenseRepository.list() }.toListDto()
            call.respond(HttpStatusCode.OK, expenses)
        }

        get("/total") {
            val total = expenseRepository.dbQuery { expenseRepository.list().sumOf { it.amount } }
            call.respond(HttpStatusCode.OK, mapOf("total" to total))
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                return@get
            }
            val expense = expenseRepository.dbQuery { expenseRepository.read(id) }

            if (expense.isPresent) {
                call.respond(HttpStatusCode.OK, expense.get().toDto())
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}