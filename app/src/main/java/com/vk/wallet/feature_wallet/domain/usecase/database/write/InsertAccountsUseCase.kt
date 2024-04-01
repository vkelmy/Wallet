package com.vk.wallet.feature_wallet.domain.usecase.database.write

import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertAccountsUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(account: List<Account>) {
        repo.insertAccount(account)
    }
}