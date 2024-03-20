package com.vk.expensetracker.feature_expensetracker.data.mapper

import com.vk.expensetracker.feature_expensetracker.data.local.entities.AccountEntity
import com.vk.expensetracker.feature_expensetracker.data.local.entities.TransactionEntity
import com.vk.expensetracker.feature_expensetracker.domain.model.Account
import com.vk.expensetracker.feature_expensetracker.domain.model.Transaction

fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        date = date,
        dateOfEntry = dateOfEntry,
        amount = amount,
        account = account,
        category = category,
        transactionType = transactionType,
        transactionTitle = transactionTitle
    )
}

fun Transaction.toTransactionEntity() : TransactionEntity {
    return TransactionEntity(
        date = date,
        dateOfEntry = dateOfEntry,
        amount = amount,
        account = account,
        category = category,
        transactionType = transactionType,
        transactionTitle = transactionTitle
    )
}