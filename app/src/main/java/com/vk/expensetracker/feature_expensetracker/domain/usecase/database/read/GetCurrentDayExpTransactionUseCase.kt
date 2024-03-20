package com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read

import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction
import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentDayExpTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> {
        return transactionRepository.getCurrentDayExpTransaction()
    }
}