package com.flipedds.expenses.adapters.out.rest

import com.flipedds.expenses.adapters.out.dtos.ExpenseDto
import com.flipedds.expenses.adapters.out.converters.toDto
import com.flipedds.expenses.adapters.out.converters.toEntity
import com.flipedds.expenses.adapters.out.converters.toListDto
import com.flipedds.expenses.usecases.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Routing.expensesRoutes(application: Application) {
    with(application) {
        route("/expenses") {
            authenticate {
                post {
                    val expense = call.receive<ExpenseDto>().toEntity()
                    val id = createExpense(expense)
                    call.respond(HttpStatusCode.Created, id to "Expense created successfully !")
                }
                put("/{id}") {
                    val id = call.parameters["id"]?.toInt()

                    if (id == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                        return@put
                    }
                    val expense = call.receive<ExpenseDto>().toEntity()
                    updateExpense(id, expense)
                    call.respond(HttpStatusCode.OK)
                }

                delete("/{id}") {
                    val id = call.parameters["id"]?.toInt()

                    if (id == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                        return@delete
                    }
                    deleteExpense(id)
                    call.respond(HttpStatusCode.NoContent)
                }
            }

            get {
                val expenses = listExpenses()
                call.respond(HttpStatusCode.OK, expenses.toListDto())
            }

            get("/total") {
                val total = totalAmountOfExpenses()
                call.respond(HttpStatusCode.OK, mapOf("total" to total))
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toInt()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                    return@get
                }
                val expense = getExpenseById(id)

                if (expense.isPresent) {
                    call.respond(HttpStatusCode.OK, expense.get().toDto())
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}