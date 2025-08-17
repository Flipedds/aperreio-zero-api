package com.flipedds.expenses

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseResponse(
    val id: Int,
    val msg: String
)