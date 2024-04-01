package com.vk.wallet.feature_wallet.data.repository

import com.vk.wallet.feature_wallet.data.local.dao.TransactionDao
import com.vk.wallet.feature_wallet.data.mapper.toAccount
import com.vk.wallet.feature_wallet.data.mapper.toAccountEntity
import com.vk.wallet.feature_wallet.data.mapper.toTransaction
import com.vk.wallet.feature_wallet.data.mapper.toTransactionEntity
import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
): TransactionRepository {
    override suspend fun insertTransaction(dailyExpense: Transaction) {
        dao.insertTransaction(transaction = dailyExpense.toTransactionEntity())
    }

    override suspend fun insertAccount(accounts: List<Account>) {
        dao.insertAccounts(accounts.map { it.toAccountEntity() })
    }

    override fun getDailyTransaction(entryDate: String): Flow<List<Transaction>> {
        return dao.getDailyTransaction(entryDate).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun getTransactionByAccount(accountType: String): Flow<List<Transaction>> {
        return dao.getTransactionByAccount(accountType).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun getAccount(account: String): Flow<Account> {
        return dao.getAccount(account).map { it.toAccount()}
    }

    override fun getAllAccounts(): Flow<List<Account>> {
        return dao.getAllAccounts().map { it ->
            it.map {
                it.toAccount()
            }
        }
    }

    override fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAllTransactions().map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun eraseTransaction() {
        dao.eraseTransaction()
    }

    override fun getCurrentDayExpTransaction(): Flow<List<Transaction>> {
        return dao.getCurrentDayExpTransaction().map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun getWeeklyExpTransaction(): Flow<List<Transaction>> {
        return dao.getWeeklyExpTransaction().map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun getMonthlyExpTransaction(): Flow<List<Transaction>> {
        return dao.getMonthlyExpTransaction().map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun get3DayTransaction(transactionType: String): Flow<List<Transaction>> {
        return dao.get3DayTransaction(transactionType).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun get7DayTransaction(transactionType: String): Flow<List<Transaction>> {
        return dao.get7DayTransaction(transactionType).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun get14DayTransaction(transactionType: String): Flow<List<Transaction>> {
        return dao.get14DayTransaction(transactionType).map { it ->
            it.map {
                it.toTransaction()
            }
        }    }

    override fun getStartOfMonthTransaction(transactionType: String): Flow<List<Transaction>> {
        return dao.getStartOfMonthTransaction(transactionType).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun getLastMonthTransaction(transactionType: String): Flow<List<Transaction>> {
        return dao.getLastMonthTransaction(transactionType).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }

    override fun getTransactionByType(transactionType: String): Flow<List<Transaction>> {
        return dao.getTransactionByType(transactionType).map { it ->
            it.map {
                it.toTransaction()
            }
        }
    }
}