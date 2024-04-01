package com.vk.wallet.feature_wallet.data.mapper

import com.vk.wallet.feature_wallet.data.local.entities.AccountEntity
import com.vk.wallet.feature_wallet.domain.model.Account

fun AccountEntity.toAccount(): Account {
    return Account(
        id = id,
        account = account,
        balance = balance,
        income = income,
        expense = expense
    )
}

fun Account.toAccountEntity() : AccountEntity {
    return AccountEntity(
        id = id,
        account = account,
        balance = balance,
        income = income,
        expense = expense
    )
}