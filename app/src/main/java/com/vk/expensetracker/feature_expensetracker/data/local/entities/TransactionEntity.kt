package com.vk.expensetracker.feature_expensetracker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction
import java.util.Date

@Entity
data class TransactionEntity(
    @PrimaryKey
    val date: Date,
    val dateOfEntry: String,
    val amount: Double,
    val account: String,
    val category: String,
    val transactionType: String,
    val transactionTitle: String
)