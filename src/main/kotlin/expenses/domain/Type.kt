package com.flipedds.expenses.domain

import kotlinx.serialization.Serializable

@Serializable
enum class Type {
    CARD_PIC_PAY,
    CARD_NEON,
    CARD_BB,
    CARD_INTER,
    CARD_NUBANK,
    PIX
}