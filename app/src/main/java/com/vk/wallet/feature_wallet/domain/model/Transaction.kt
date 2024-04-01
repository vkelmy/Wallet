package com.vk.wallet.feature_wallet.domain.model

import java.util.Date

data class Transaction(
    val date: Date,
    val dateOfEntry: String,
    val amount: Double,
    val account: String,
    val category: String,
    val transactionType: String,
    val transactionTitle: String
)