package com.vk.expensetracker.feature_expensetracker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vk.expensetracker.feature_expensetracker.domain.model.Account

@Entity
data class AccountEntity(
    @PrimaryKey
    val id: Int? = null,
    val account: String,
    var balance: Double,
    var income: Double,
    var expense: Double
)