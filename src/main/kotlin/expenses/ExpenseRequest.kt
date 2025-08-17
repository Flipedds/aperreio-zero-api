package com.flipedds.expenses

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseRequest(
    val description: String,
    val amount: Double,
    val type: Type
)