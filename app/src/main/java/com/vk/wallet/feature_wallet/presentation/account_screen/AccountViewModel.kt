package com.vk.wallet.feature_wallet.presentation.account_screen

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.usecase.database.DatabaseUseCases
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getDatabaseUseCases: DatabaseUseCases,
    private val getDatastoreUseCases: DatastoreUseCases
): ViewModel() {

    private var _transactions = MutableStateFlow(mapOf<String, List<Transaction>>())
    val transactions = _transactions

    private var _allAccounts = MutableStateFlow(emptyList<Account>())
    val allAccounts = _allAccounts

    private var _selectedCurrencyCode = MutableStateFlow(String())
    val selectedCurrencyCode = _selectedCurrencyCode


    init {
        currencyFormat()
        viewModelScope.launch(IO) {
            getDatabaseUseCases.getAccountsUseCase().collect { accounts ->
                _allAccounts.value = accounts
            }
        }
    }

    private fun currencyFormat() {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.getCurrencyUseCase().collect { selectedCurrency ->
                _selectedCurrencyCode.value = selectedCurrency
            }
        }
    }

    fun getTransaction(accountType: String) {
        viewModelScope.launch(IO) {
            getDatabaseUseCases.getTransactionByAccount(accountType).collect { allTrx ->
                _transactions.value = allTrx.reversed().groupBy { trx ->
                    getFormattedDate(trx.date)
                }
            }
        }
    }

    private fun getFormattedDate(date: Date): String {
        val dayOfWeek = DateFormat.format("EEE", date)
        val day = DateFormat.format("dd", date)
        val month = DateFormat.format("MMM", date)

        return "$dayOfWeek $day, $month"
    }
}