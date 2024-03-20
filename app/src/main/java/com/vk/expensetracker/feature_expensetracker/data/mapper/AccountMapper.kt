package com.vk.expensetracker.feature_expensetracker.data.mapper

import com.vk.expensetracker.feature_expensetracker.data.local.entities.AccountEntity
import com.vk.expensetracker.feature_expensetracker.domain.model.Account

fun AccountEntity.toAccount(): Account {
    return Account(
        account = account,
        balance = balance,
        income = income,
        expense = expense
    )
}

fun Account.toAccountEntity() : AccountEntity {
    return AccountEntity(
        account = account,
        balance = balance,
        income = income,
        expense = expense
    )
}