package com.vk.wallet.feature_wallet.domain.usecase.database.read

import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(private val repo: TransactionRepository) {
    operator fun invoke(): Flow<List<Account>> {
        return repo.getAllAccounts()
    }
}