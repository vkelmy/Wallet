package com.vk.wallet.feature_wallet.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun writeOnBoardingKeyToDataStore(completed: Boolean)

    suspend fun readOnBoardingKeyFromDataStore(): Flow<Boolean>

    suspend fun writeCurrencyToDataStore(currency: String)

    suspend fun readCurrencyFromDataStore(): Flow<String>

    suspend fun writeExpenseLimitToDataStore(amount: Double)

    suspend fun readExpenseLimitFromDataStore(): Flow<Double>

    suspend fun writeLimitKeyToDataStore(enabled: Boolean)

    suspend fun readLimitKeyFromDataStore(): Flow<Boolean>

    suspend fun writeLimitDurationToDataStore(duration: Int)

    suspend fun readLimitDurationFromDataStore(): Flow<Int>

    suspend fun eraseDatastore()

    suspend fun writeDarkMode(mode: Boolean)

    suspend fun readDarkMode(): Flow<Boolean>
}