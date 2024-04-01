package com.vk.wallet.feature_wallet.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val account: String,
    var balance: Double,
    var income: Double,
    var expense: Double
)