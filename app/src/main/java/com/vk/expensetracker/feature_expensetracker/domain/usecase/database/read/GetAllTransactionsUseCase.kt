package com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read

import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction
import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(private val repo: TransactionRepository) {
    operator fun invoke(): Flow<List<Transaction>?> {
        return repo.getAllTransactions()
    }
}