package com.vk.expensetracker.feature_expensetracker.domain.usecase.database

import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.Get14DayTransaction
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.Get3DayTransaction
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.Get7DayTransaction
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetAccountUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetAccountsUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetAllTransactionsUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetCurrentDayExpTransactionUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetDailyTransactionUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetLastMonthTransaction
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetMonthlyExpTransactionUse
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetStartOfMonthTransaction
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetTransactionByAccount
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetTransactionByTypeUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.read.GetWeeklyExpTransactionUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.write.EraseTransactionUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.write.InsertAccountsUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.database.write.InsertNewTransactionUseCase

data class DatabaseUseCases(
    val get3DayTransaction: Get3DayTransaction,
    val get7DayTransaction: Get7DayTransaction,
    val get14DayTransaction: Get14DayTransaction,
    val getAccountsUseCase: GetAccountsUseCase,
    val getAccountUseCase: GetAccountUseCase,
    val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    val getCurrentDayExpTransactionUseCase: GetCurrentDayExpTransactionUseCase,
    val getDailyTransactionUseCase: GetDailyTransactionUseCase,
    val getLastMonthTransaction: GetLastMonthTransaction,
    val getMonthlyExpTransactionUse: GetMonthlyExpTransactionUse,
    val getStartOfMonthTransaction: GetStartOfMonthTransaction,
    val getTransactionByAccount: GetTransactionByAccount,
    val getTransactionByTypeUseCase: GetTransactionByTypeUseCase,
    val getWeeklyExpTransactionUseCase: GetWeeklyExpTransactionUseCase,

    val eraseTransactionUseCase: EraseTransactionUseCase,
    val insertAccountsUseCase: InsertAccountsUseCase,
    val insertNewTransactionUseCase: InsertNewTransactionUseCase,
)