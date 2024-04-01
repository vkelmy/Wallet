package com.vk.wallet.feature_wallet.domain.usecase.database.read

import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Get14DayTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transactionType: String) : Flow<List<Transaction>> {
        return transactionRepository.get14DayTransaction(transactionType)
    }
}