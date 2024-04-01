package com.vk.wallet.feature_wallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vk.wallet.feature_wallet.data.local.converter.DateConverter
import com.vk.wallet.feature_wallet.data.local.dao.TransactionDao
import com.vk.wallet.feature_wallet.data.local.entities.AccountEntity
import com.vk.wallet.feature_wallet.data.local.entities.TransactionEntity

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class TransactionDatabase : RoomDatabase() {
    abstract val transactionDao: TransactionDao
}