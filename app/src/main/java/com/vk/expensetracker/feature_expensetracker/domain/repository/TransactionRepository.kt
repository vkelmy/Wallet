package com.vk.expensetracker.feature_expensetracker.domain.repository

import com.vk.expensetracker.feature_expensetracker.domain.model.Account
import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertTransaction(dailyExpense: Transaction)

    suspend fun insertAccount(accounts: List<Account>)

    fun getDailyTransaction(entryDate: String): Flow<List<Transaction>>

    fun getTransactionByAccount(accountType: String): Flow<List<Transaction>>

    fun getAccount(account: String): Flow<Account>

    fun getAllAccounts(): Flow<List<Account>>

    fun getAllTransactions(): Flow<List<Transaction>>

    fun eraseTransaction()

    fun getCurrentDayExpTransaction(): Flow<List<Transaction>>

    fun getWeeklyExpTransaction(): Flow<List<Transaction>>

    fun getMonthlyExpTransaction(): Flow<List<Transaction>>

    fun get3DayTransaction(transactionType: String): Flow<List<Transaction>>

    fun get7DayTransaction(transactionType: String): Flow<List<Transaction>>

    fun get14DayTransaction(transactionType: String): Flow<List<Transaction>>

    fun getStartOfMonthTransaction(transactionType: String): Flow<List<Transaction>>

    fun getLastMonthTransaction(transactionType: String): Flow<List<Transaction>>

    fun getTransactionByType(transactionType: String): Flow<List<Transaction>>
}