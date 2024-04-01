package com.vk.wallet.feature_wallet.domain.usecase.database.read

import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyTransactionUseCase @Inject constructor(private val repo: TransactionRepository) {

    operator fun invoke(entryDate: String): Flow<List<Transaction>?> {
        return repo.getDailyTransaction(entryDate)
    }
}