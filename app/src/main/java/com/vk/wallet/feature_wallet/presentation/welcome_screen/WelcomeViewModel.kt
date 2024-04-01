package com.vk.wallet.feature_wallet.presentation.welcome_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.domain.model.CurrencyModel
import com.vk.wallet.feature_wallet.domain.usecase.GetCurrencyUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.DatabaseUseCases
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import com.vk.wallet.feature_wallet.presentation.util.AccountsType
import com.vk.wallet.feature_wallet.presentation.welcome_screen.components.OnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val datastoreUseCases: DatastoreUseCases,
    private val databaseUseCases: DatabaseUseCases,
    getCurrencyUseCase: GetCurrencyUseCase
): ViewModel() {

    private var _countryCurrencies = mutableStateOf(emptyMap<Char, List<CurrencyModel>>())
    val countryCurrencies = _countryCurrencies

    init {
        _countryCurrencies.value = getCurrencyUseCase().groupBy { it.country[0] }
    }

    val listOfPages: State<List<OnBoardingPage>> = mutableStateOf(listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage
    ))

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(IO) {
            datastoreUseCases.editOnBoardingUseCase(completed = completed)
        }
    }

    fun saveCurrency(currency: String) {
        viewModelScope.launch(IO) {
            datastoreUseCases.editCurrencyUseCase(currency)
        }
    }

    fun createAccounts() {
        viewModelScope.launch(IO) {
            databaseUseCases.insertAccountsUseCase.invoke(
                listOf(
                    Account(1, AccountsType.CASH.title, 0.0, 0.0, 0.0),
                    Account(2, AccountsType.PIX.title, 0.0, 0.0, 0.0),
                    Account(3, AccountsType.CARD.title, 0.0, 0.0, 0.0)
                )
            )
        }
    }
}