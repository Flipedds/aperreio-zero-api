package com.flipedds.expenses.domain.entities

import com.flipedds.expenses.domain.valueobjects.Type
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Expense(
    val id: Int? = null,
    val description: String,
    val amount: Double,
    val date: LocalDateTime,
    val type: Type
)