package com.vk.expensetracker.feature_expensetracker.domain.usecase.database.write

import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction
import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertNewTransactionUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(dailyExpense: Transaction) {
        repo.insertTransaction(dailyExpense)
    }
}