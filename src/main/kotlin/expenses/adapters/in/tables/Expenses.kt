package com.flipedds.expenses.adapters.`in`.tables

import com.flipedds.expenses.domain.Type
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Expenses : IntIdTable() {
     val description = varchar("description", 255)
     val amount = double("amount")
     val date = datetime("date")
     val type = enumeration<Type>("type")
 }