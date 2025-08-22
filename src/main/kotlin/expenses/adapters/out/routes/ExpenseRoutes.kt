package com.flipedds.expenses.adapters.out.routes

import com.flipedds.expenses.domain.controllers.ExpenseController
import com.flipedds.expenses.adapters.out.dtos.ExpenseDto
import com.flipedds.expenses.adapters.out.converters.toDto
import com.flipedds.expenses.adapters.out.converters.toEntity
import com.flipedds.expenses.adapters.out.converters.toListDto
import com.flipedds.expenses.domain.usecases.createExpense
import com.flipedds.expenses.domain.usecases.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Routing.expensesRoutes(expenseController: ExpenseController) {
    with(application) {
        route("/expenses") {
            authenticate {
                post {
                    val expense = call.receive<ExpenseDto>().toEntity()
                    val id = expenseController.createExpense(expense)
                    call.respond(HttpStatusCode.Created, id to "Expense created successfully !")
                }
                put("/{id}") {
                    val id = call.parameters["id"]?.toInt()

                    if (id == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                        return@put
                    }
                    val expense = call.receive<ExpenseDto>().toEntity()
                    expenseController.updateExpense(id, expense)
                    call.respond(HttpStatusCode.OK)
                }

                delete("/{id}") {
                    val id = call.parameters["id"]?.toInt()

                    if (id == null) {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                        return@delete
                    }
                    expenseController.deleteExpense(id)
                    call.respond(HttpStatusCode.NoContent)
                }
            }

            get {
                val expenses = expenseController.listExpenses()
                call.respond(HttpStatusCode.OK, expenses.toListDto())
            }

            get("/total") {
                val total = expenseController.totalAmountOfExpenses()
                call.respond(HttpStatusCode.OK, mapOf("total" to total))
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toInt()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid ID"))
                    return@get
                }
                val expense = expenseController.getExpenseById(id)

                if (expense.isPresent) {
                    call.respond(HttpStatusCode.OK, expense.get().toDto())
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}