package com.vk.expensetracker.feature_expensetracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vk.expensetracker.feature_expensetracker.common.TransactionType
import com.vk.expensetracker.feature_expensetracker.data.local.entities.AccountEntity
import com.vk.expensetracker.feature_expensetracker.data.local.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccounts(accounts: List<AccountEntity>)

    @Query("select * from accountentity where account = :account")
    fun getAccount(account: String): Flow<AccountEntity>

    @Query("SELECT * FROM accountentity")
    fun getAllAccounts(): Flow<List<AccountEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry = :entryDate")
    fun getDailyTransaction(entryDate: String) : Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE account = :accountType")
    fun getTransactionByAccount(accountType: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity")
    fun getAllTransactions() : Flow<List<TransactionEntity>>

    @Query("DELETE FROM transactionentity")
    fun eraseTransaction()

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry = date('now', 'localtime') AND transactionType = :transactionType")
    fun getCurrentDayExpTransaction(transactionType: String = TransactionType.EXPENSE.title): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry BETWEEN date('now', '-7 day') AND date('now', 'localtime') AND transactionType = :transactionType")
    fun getWeeklyExpTransaction(transactionType: String = TransactionType.EXPENSE.title): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry BETWEEN date('now', '-1 month') AND date('now', 'localtime') AND transactionType = :transactionType")
    fun getMonthlyExpTransaction(transactionType: String = TransactionType.EXPENSE.title): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry >= date('now', '-3 day') AND dateOfEntry < date('now', 'localtime') AND transactionType = :transactionType")
    fun get3DayTransaction(transactionType: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry >= date('now', '-7 day') AND dateOfEntry < date('now', 'localtime') AND transactionType = :transactionType")
    fun get7DayTransaction(transactionType: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry >= date('now', '-14 day') AND dateOfEntry < date('now', 'localtime') AND transactionType = :transactionType")
    fun get14DayTransaction(transactionType: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry >= date('now', 'start of month') AND dateOfEntry < date('now', 'localtime') AND transactionType = :transactionType")
    fun getStartOfMonthTransaction(transactionType: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE dateOfEntry >= date('now', '-1 month') AND dateOfEntry < date('now', 'localtime') AND transactionType = :transactionType")
    fun getLastMonthTransaction(transactionType: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionentity WHERE transactionType = :transactionType")
    fun getTransactionByType(transactionType: String): Flow<List<TransactionEntity>>
}