package com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read

import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction
import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastMonthTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transactionType: String) : Flow<List<Transaction>> {
        return transactionRepository.getLastMonthTransaction(transactionType)
    }
}