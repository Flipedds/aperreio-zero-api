package com.flipedds.expenses.adapters.out.converters

import com.flipedds.expenses.adapters.out.dtos.ExpenseDto
import com.flipedds.expenses.domain.entities.Expense
import kotlinx.datetime.LocalDateTime
import java.time.format.DateTimeFormatter

fun ExpenseDto.toEntity(): Expense {
    return Expense(
        description = this.description,
        amount = this.amount,
        date = LocalDateTime.parse(
            java.time.LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        ),
        type = this.type
    )
}

fun List<Expense>.toListDto(): List<ExpenseDto>{
    return this.map { it -> it.toDto() }
}

fun Expense.toDto(): ExpenseDto {
    return ExpenseDto(this.description, this.amount, this.type)
}