package com.vk.wallet.feature_wallet.domain.model

data class Account(
    val id: Int,
    val account: String,
    var balance: Double,
    var income: Double,
    var expense: Double
)