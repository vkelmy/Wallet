package com.vk.wallet.feature_wallet.presentation.insight_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.usecase.database.DatabaseUseCases
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import com.vk.wallet.feature_wallet.presentation.util.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsightViewModel @Inject constructor(
    private val getDatabaseUseCases: DatabaseUseCases,
    private val getDatastoreUseCases: DatastoreUseCases
): ViewModel() {

    private var _tabButton = MutableStateFlow(TransactionType.INCOME)
    val tabButton: StateFlow<TransactionType> = _tabButton

    private var _filteredTransaction = MutableStateFlow(emptyList<Transaction>())
    val filteredTransaction: StateFlow<List<Transaction>> = _filteredTransaction

    private var _selectedCurrencyCode = MutableStateFlow(String())
    val selectedCurrencyCode = _selectedCurrencyCode


    init {
        getFilteredTransaction()
        savedCurrency()
    }

    fun selectTabButton(tab: TransactionType) {
        _tabButton.value = tab
        getFilteredTransaction()
    }

    fun getFilteredTransaction(filterTimeFrame: Int = 5) {
        viewModelScope.launch(IO) {
            if (_tabButton.value == TransactionType.INCOME) {
                filterTransaction(filterTimeFrame, TransactionType.INCOME.title)
            } else {
                filterTransaction(filterTimeFrame, TransactionType.EXPENSE.title)
            }
        }
    }

    private suspend fun filterTransaction(
        filterTimeFrame: Int,
        transactionType: String = TransactionType.INCOME.title
    ) {
        when (filterTimeFrame) {
            0 -> {
                getDatabaseUseCases.get3DayTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults
                }
            }
            1 -> {
                getDatabaseUseCases.get7DayTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults
                }
            }
            2 -> {
                getDatabaseUseCases.get14DayTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults
                }
            }
            3 -> {
                getDatabaseUseCases.getStartOfMonthTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults
                }
            }
            4 -> {
                getDatabaseUseCases.getLastMonthTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults
                }
            }
            5 -> {
                getDatabaseUseCases.getTransactionByTypeUseCase(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults
                }
            }
        }
    }

    private fun savedCurrency() {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.getCurrencyUseCase().collect { selectedCurrency ->
                _selectedCurrencyCode.value = selectedCurrency
            }
        }
    }
}