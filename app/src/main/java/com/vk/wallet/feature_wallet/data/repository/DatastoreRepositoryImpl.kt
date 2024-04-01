package com.vk.wallet.feature_wallet.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import com.vk.wallet.feature_wallet.common.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "expense_key_store")

@Singleton
class DatastoreRepositoryImpl @Inject constructor(context: Context): DatastoreRepository {

    private val datastore = context.datastore
    private val onBoardingKey = booleanPreferencesKey(Constants.WELCOME_KEY)
    private val limitKey = booleanPreferencesKey(Constants.LIMIT_KEY)
    private val selectedCurrency = stringPreferencesKey(Constants.CURRENCY_KEY)
    private val expenseLimit = doublePreferencesKey(Constants.EXPENSE_LIMIT_KEY)
    private val limitDuration = intPreferencesKey(Constants.LIMIT_DURATION)
    private val themeMode = booleanPreferencesKey(Constants.THEME_MODE)

    override suspend fun writeOnBoardingKeyToDataStore(completed: Boolean) {
        datastore.edit { store ->
            store[onBoardingKey] = completed
        }
    }

    override suspend fun readOnBoardingKeyFromDataStore(): Flow<Boolean> {
        val preferences = datastore.data
        return flow {
            preferences.collect { pref ->
                emit(pref[onBoardingKey] ?: false)
            }
        }
    }

    override suspend fun writeCurrencyToDataStore(currency: String) {
        datastore.edit { store ->
            store[selectedCurrency] = currency
        }
    }

    override suspend fun readCurrencyFromDataStore(): Flow<String> {
        val preferences = datastore.data
        return flow {
            preferences.collect { pref ->
                emit(pref[selectedCurrency] ?: String())
            }
        }
    }

    override suspend fun writeExpenseLimitToDataStore(amount: Double) {
        datastore.edit { store ->
            store[expenseLimit] = amount
        }
    }

    override suspend fun readExpenseLimitFromDataStore(): Flow<Double> {
        val preferences = datastore.data
        return flow {
            preferences.collect { pref ->
                emit(pref[expenseLimit] ?: 0.0)
            }
        }
    }

    override suspend fun writeLimitKeyToDataStore(enabled: Boolean) {
        datastore.edit { store ->
            store[limitKey] = enabled
        }
    }

    override suspend fun readLimitKeyFromDataStore(): Flow<Boolean> {
        val preferences = datastore.data
        return flow {
            preferences.collect { pref ->
                emit(pref[limitKey] ?: false)
            }
        }
    }

    override suspend fun writeLimitDurationToDataStore(duration: Int) {
        datastore.edit { store ->
            store[limitDuration] = duration
        }
    }

    override suspend fun readLimitDurationFromDataStore(): Flow<Int> {
        val preferences = datastore.data
        return flow {
            preferences.collect { pref ->
                emit(pref[limitDuration] ?: 0)
            }
        }
    }

    override suspend fun eraseDatastore() {
        datastore.edit {
            it.remove(limitKey)
            it.remove(limitDuration)
            it.remove(expenseLimit)
            it.remove(themeMode)
        }
    }

    override suspend fun writeDarkMode(mode: Boolean) {
        datastore.edit { store ->
            store[themeMode] = mode
        }
    }

    override suspend fun readDarkMode(): Flow<Boolean> {
        val preferences = datastore.data
        return flow {
            preferences.collect { pref ->
                emit(pref[themeMode] ?: false)
            }
        }
    }
}