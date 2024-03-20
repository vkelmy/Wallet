package com.vk.expensetracker.feature_expensetracker.domain.usecase.database.write

import com.vk.expensetracker.feature_expensetracker.domain.model.Account
import com.vk.expensetracker.feature_expensetracker.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertAccountsUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(account: List<Account>) {
        repo.insertAccount(account)
    }
}