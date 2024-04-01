package com.vk.wallet.feature_wallet.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.usecase.datastore.read.GetOnBoardingKeyUseCase
import com.vk.wallet.feature_wallet.presentation.navigation.Screen
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getOnBoardingKeyUseCase: GetOnBoardingKeyUseCase
): ViewModel() {

    private var _startDestination = MutableStateFlow(Screen.WelcomeScreen.route)
    var startDestination = _startDestination

    init {
        viewModelScope.launch(IO) {
            getOnBoardingKeyUseCase().collect { completed ->
                if (completed)
                    _startDestination.value = Screen.HomeScreen.route
            }
        }
    }
}