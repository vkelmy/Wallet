package com.vk.expensetracker.feature_expensetracker.domain.usecase.database.write

import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import javax.inject.Inject

class EraseTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    operator fun invoke() {
        repository.eraseTransaction()
    }
}