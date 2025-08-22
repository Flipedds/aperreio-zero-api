package com.flipedds.expenses.domain.controllers

import com.flipedds.expenses.domain.interfaces.IExpenseRepository

class ExpenseController(val expenseRepository: IExpenseRepository)