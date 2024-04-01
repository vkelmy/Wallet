package com.vk.wallet.feature_wallet.data.mapper

import com.vk.wallet.feature_wallet.data.local.entities.TransactionEntity
import com.vk.wallet.feature_wallet.domain.model.Transaction

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