package com.vk.expensetracker.feature_expensetracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vk.expensetracker.feature_expensetracker.data.local.converter.DateConverter
import com.vk.expensetracker.feature_expensetracker.data.local.dao.TransactionDao
import com.vk.expensetracker.feature_expensetracker.data.local.entities.AccountEntity
import com.vk.expensetracker.feature_expensetracker.data.local.entities.TransactionEntity

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