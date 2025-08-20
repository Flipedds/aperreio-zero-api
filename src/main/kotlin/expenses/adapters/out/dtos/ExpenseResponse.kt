package com.flipedds.expenses.adapters.out.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseResponse(
    val id: Int,
    val msg: String
)