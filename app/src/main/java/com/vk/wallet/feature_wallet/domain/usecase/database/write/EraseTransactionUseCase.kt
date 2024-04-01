package com.vk.wallet.feature_wallet.domain.usecase.database.write

import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import javax.inject.Inject

class EraseTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    operator fun invoke() {
        repository.eraseTransaction()
    }
}