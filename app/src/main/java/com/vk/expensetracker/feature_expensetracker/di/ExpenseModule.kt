package com.vk.expensetracker.feature_expensetracker.di

import android.content.Context
import androidx.room.Room
import com.vk.expensetracker.feature_expensetracker.data.local.TransactionDatabase
import com.vk.expensetracker.feature_expensetracker.data.local.dao.TransactionDao
import com.vk.expensetracker.feature_expensetracker.data.repository.DatastoreRepositoryImpl
import com.vk.expensetracker.feature_expensetracker.data.repository.TransactionRepositoryImpl
import com.vk.expensetracker.feature_expensetracker.domain.repository.DatastoreRepository
import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExpenseModule {

    @Provides
    @Singleton
    fun provideDatastoreRepository(@ApplicationContext context: Context): DatastoreRepository {
        return DatastoreRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: TransactionDatabase) = database.transactionDao

    @Provides
    @Singleton
    fun provideTransactionRepository(transactionDao: TransactionDao): TransactionRepository {
        return TransactionRepositoryImpl(transactionDao)
    }

    @Provides
    @Singleton
    fun provideTransactionDatabase(@ApplicationContext context: Context): TransactionDatabase {
        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            "transactionDB"
        )
            .build()
    }
}