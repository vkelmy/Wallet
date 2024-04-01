package com.vk.wallet.feature_wallet.presentation.setting_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.domain.usecase.database.DatabaseUseCases
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import com.vk.wallet.feature_wallet.presentation.util.AccountsType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getDatabaseUseCases: DatabaseUseCases,
    private val getDatastoreUseCases: DatastoreUseCases
): ViewModel() {

    private var _currency = MutableStateFlow(String())
    val currency = _currency

    private var _expenseLimit = MutableStateFlow(0.0)
    val expenseLimit = _expenseLimit

    private var _expenseLimitDuration = MutableStateFlow(0)
    val expenseLimitDuration = _expenseLimitDuration

    private var _reminderLimit = MutableStateFlow(false)
    val reminderLimit = _reminderLimit


    init {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.getCurrencyUseCase().collect { selectedCurrency ->
                _currency.value = selectedCurrency
            }
        }

        viewModelScope.launch(IO) {
            getDatastoreUseCases.getExpenseLimitUseCase().collect { expenseAmount ->
                _expenseLimit.value = expenseAmount
            }
        }

        viewModelScope.launch(IO) {
            getDatastoreUseCases.getLimitKeyUseCase().collect { limitKey ->
                _reminderLimit.value = limitKey
            }
        }

        viewModelScope.launch(IO) {
            getDatastoreUseCases.getLimitDurationUseCase().collect { duration ->
                _expenseLimitDuration.value = duration
            }
        }
    }

    fun eraseTransaction() {
        viewModelScope.launch(IO) {
            getDatabaseUseCases.insertAccountsUseCase(
                listOf(
                    Account(1, AccountsType.CASH.title, 0.0, 0.0, 0.0),
                    Account(2, AccountsType.PIX.title, 0.0, 0.0, 0.0),
                    Account(3, AccountsType.CARD.title, 0.0, 0.0, 0.0)
                )
            )

            getDatabaseUseCases.eraseTransactionUseCase()
            getDatastoreUseCases.eraseDatastoreUseCase()
        }
    }

    fun editExpenseLimit(amount: Double) {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.editExpenseLimitUseCase(amount)
        }
    }

    fun editLimitKey(enabled: Boolean) {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.editLimitKeyUseCase(enabled)
        }
    }

    fun editLimitDuration(duration: Int) {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.editLimitDurationUseCase(duration)
        }
    }
}