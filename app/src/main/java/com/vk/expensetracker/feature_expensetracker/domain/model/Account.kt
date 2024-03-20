package com.vk.expensetracker.feature_expensetracker.domain.model

data class Account(
    val account: String,
    var balance: Double,
    var income: Double,
    var expense: Double
)