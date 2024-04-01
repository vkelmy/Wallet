package com.vk.wallet.feature_wallet.domain.usecase.database.write

import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertNewTransactionUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(dailyExpense: Transaction) {
        repo.insertTransaction(dailyExpense)
    }
}