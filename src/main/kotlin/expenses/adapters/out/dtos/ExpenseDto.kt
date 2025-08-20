package com.flipedds.expenses.adapters.out.dtos

import com.flipedds.expenses.domain.Type
import kotlinx.serialization.Serializable

@Serializable
data class ExpenseDto(
    val description: String,
    val amount: Double,
    val type: Type
)