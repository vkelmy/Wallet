package com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore

import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.read.GetCurrencyUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.read.GetExpenseLimitUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.read.GetLimitDurationUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.read.GetLimitKeyUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.read.GetOnBoardingKeyUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write.EditCurrencyUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write.EditExpenseLimitUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write.EditLimitDurationUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write.EditLimitKeyUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write.EditOnBoardingUseCase
import com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write.EraseDatastoreUseCase

data class DatastoreUseCases(
    val getCurrencyUseCase: GetCurrencyUseCase,
    val getExpenseLimitUseCase: GetExpenseLimitUseCase,
    val getLimitDurationUseCase: GetLimitDurationUseCase,
    val getLimitKeyUseCase: GetLimitKeyUseCase,
    val getOnBoardingKeyUseCase: GetOnBoardingKeyUseCase,

    val editCurrencyUseCase: EditCurrencyUseCase,
    val editExpenseLimitUseCase: EditExpenseLimitUseCase,
    val editLimitDurationUseCase: EditLimitDurationUseCase,
    val editLimitKeyUseCase: EditLimitKeyUseCase,
    val editOnBoardingUseCase: EditOnBoardingUseCase,
    val eraseDatastoreUseCase: EraseDatastoreUseCase,
)