package com.vk.wallet.feature_wallet.di

import android.content.Context
import androidx.room.Room
import com.vk.wallet.feature_wallet.data.local.TransactionDatabase
import com.vk.wallet.feature_wallet.data.local.dao.TransactionDao
import com.vk.wallet.feature_wallet.data.repository.DatastoreRepositoryImpl
import com.vk.wallet.feature_wallet.data.repository.TransactionRepositoryImpl
import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import com.vk.wallet.feature_wallet.domain.usecase.database.DatabaseUseCases
import com.vk.wallet.feature_wallet.domain.usecase.database.read.Get14DayTransaction
import com.vk.wallet.feature_wallet.domain.usecase.database.read.Get3DayTransaction
import com.vk.wallet.feature_wallet.domain.usecase.database.read.Get7DayTransaction
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetAccountUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetAccountsUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetAllTransactionsUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetCurrentDayExpTransactionUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetDailyTransactionUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetLastMonthTransaction
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetMonthlyExpTransactionUse
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetStartOfMonthTransaction
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetTransactionByAccount
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetTransactionByTypeUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.read.GetWeeklyExpTransactionUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.write.EraseTransactionUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.write.InsertAccountsUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.write.InsertNewTransactionUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetCurrencyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetExpenseLimitUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetLimitDurationUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetLimitKeyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetOnBoardingKeyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetThemeMode
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditCurrencyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditExpenseLimitUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditLimitDurationUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditLimitKeyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditOnBoardingUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditThemeMode
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EraseDatastoreUseCase
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

    @Provides
    @Singleton
    fun provideDatabaseUseCases(repository: TransactionRepository): DatabaseUseCases {
        return DatabaseUseCases(
            get3DayTransaction = Get3DayTransaction(repository),
            get7DayTransaction = Get7DayTransaction(repository),
            get14DayTransaction = Get14DayTransaction(repository),
            getAccountsUseCase = GetAccountsUseCase(repository),
            getAccountUseCase = GetAccountUseCase(repository),
            getAllTransactionsUseCase = GetAllTransactionsUseCase(repository),
            getCurrentDayExpTransactionUseCase = GetCurrentDayExpTransactionUseCase(repository),
            getDailyTransactionUseCase = GetDailyTransactionUseCase(repository),
            getLastMonthTransaction = GetLastMonthTransaction(repository),
            getMonthlyExpTransactionUse = GetMonthlyExpTransactionUse(repository),
            getStartOfMonthTransaction = GetStartOfMonthTransaction(repository),
            getTransactionByAccount = GetTransactionByAccount(repository),
            getTransactionByTypeUseCase = GetTransactionByTypeUseCase(repository),
            getWeeklyExpTransactionUseCase = GetWeeklyExpTransactionUseCase(repository),
            eraseTransactionUseCase = EraseTransactionUseCase(repository),
            insertAccountsUseCase = InsertAccountsUseCase(repository),
            insertNewTransactionUseCase = InsertNewTransactionUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDatastoreUseCases(repository: DatastoreRepository): DatastoreUseCases {
        return DatastoreUseCases(
            getCurrencyUseCase = GetCurrencyUseCase(repository),
            getExpenseLimitUseCase = GetExpenseLimitUseCase(repository),
            getLimitDurationUseCase = GetLimitDurationUseCase(repository),
            getLimitKeyUseCase = GetLimitKeyUseCase(repository),
            getOnBoardingKeyUseCase = GetOnBoardingKeyUseCase(repository),
            editCurrencyUseCase = EditCurrencyUseCase(repository),
            editExpenseLimitUseCase = EditExpenseLimitUseCase(repository),
            editLimitDurationUseCase = EditLimitDurationUseCase(repository),
            editLimitKeyUseCase = EditLimitKeyUseCase(repository),
            editOnBoardingUseCase = EditOnBoardingUseCase(repository),
            eraseDatastoreUseCase = EraseDatastoreUseCase(repository),
            getThemeMode = GetThemeMode(repository),
            editThemeMode = EditThemeMode(repository)
        )
    }
}