package com.flipedds

import com.flipedds.expenses.adapters.out.dtos.ExpenseDto
import com.flipedds.expenses.domain.valueobjects.Type
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ApplicationTest {
    @Test
    fun testGetExpenses() = testApplication {
        application {
            module()
        }
        client.get("/expenses").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun testGetExpenseById() = testApplication {
        application {
            module()
        }
        client.get("/expenses/1").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun testNotGetExpenseByIdNotFound() = testApplication {
        application {
            module()
        }
        client.get("/expenses/100000").apply {
            assertEquals(HttpStatusCode.NotFound, status)
        }
    }

    @Test
    fun testPostExpense() = testApplication {
        application {
            module()
        }

        client = createClient {
            install(ContentNegotiation){
                json()
            }
        }

        val token = client.post("/auth") {
            contentType(ContentType.Application.Json)
            body
        }.body<HashMap<String, String>>()["token"]

        println(token)

        val response = client.post("/expenses") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
            setBody(ExpenseDto("teste", 100.10, Type.PIX))
        }

        val id = response.body<Pair<Int, String>>().first
        val msg = response.body<Pair<Int, String>>().second

        println(id)
        println(msg)

        assertIs<Int>(id)
        assertEquals("Expense created successfully !", msg)
    }

    @Test
    fun testDeleteExpense() = testApplication {
        application {
            module()
        }

        client = createClient {
            install(ContentNegotiation){
                json()
            }
        }

        val token = client.post("/auth") {
            contentType(ContentType.Application.Json)
            body
        }.body<HashMap<String, String>>()["token"]

        println(token)

        val response = client.post("/expenses") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
            setBody(ExpenseDto("teste", 100.10, Type.PIX))
        }

        val id = response.body<Pair<Int, String>>().first

        val delete = client.delete("/expenses/$id") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
        }

        assertEquals(delete.status, HttpStatusCode.NoContent)
    }
    @Test
    fun testUpdateExpense() = testApplication {
        application {
            module()
        }

        client = createClient {
            install(ContentNegotiation){
                json()
            }
        }

        val token = client.post("/auth") {
            contentType(ContentType.Application.Json)
            body
        }.body<HashMap<String, String>>()["token"]

        println(token)

        val response = client.post("/expenses") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
            setBody(ExpenseDto("teste", 100.10, Type.PIX))
        }

        val id = response.body<Pair<Int, String>>().first

        val update = client.put("/expenses/$id") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
            setBody(ExpenseDto("teste update", 200.00, Type.PIX))
        }

        assertEquals(update.status, HttpStatusCode.OK)
    }
}
