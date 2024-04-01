package com.vk.wallet.feature_wallet.domain.usecase.datastore

import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetCurrencyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetExpenseLimitUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetLimitDurationUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetLimitKeyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetOnBoardingKeyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetThemeMode
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditCurrencyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditExpenseLimitUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditLimitDurationUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditLimitKeyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditOnBoardingUseCase
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EditThemeMode
import com.vk.wallet.feature_wallet.domain.usecase.datastore.write.EraseDatastoreUseCase

data class DatastoreUseCases(
    val getCurrencyUseCase: GetCurrencyUseCase,
    val getExpenseLimitUseCase: GetExpenseLimitUseCase,
    val getLimitDurationUseCase: GetLimitDurationUseCase,
    val getLimitKeyUseCase: GetLimitKeyUseCase,
    val getOnBoardingKeyUseCase: GetOnBoardingKeyUseCase,
    val getThemeMode: GetThemeMode,

    val editCurrencyUseCase: EditCurrencyUseCase,
    val editExpenseLimitUseCase: EditExpenseLimitUseCase,
    val editLimitDurationUseCase: EditLimitDurationUseCase,
    val editLimitKeyUseCase: EditLimitKeyUseCase,
    val editOnBoardingUseCase: EditOnBoardingUseCase,
    val editThemeMode: EditThemeMode,
    val eraseDatastoreUseCase: EraseDatastoreUseCase,
)