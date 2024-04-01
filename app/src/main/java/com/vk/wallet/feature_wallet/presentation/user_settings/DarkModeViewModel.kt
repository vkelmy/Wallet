package com.vk.wallet.feature_wallet.presentation.user_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DarkModeViewModel @Inject constructor(
    private val getDatastoreUseCases: DatastoreUseCases
): ViewModel() {

    private var _themeMode = MutableStateFlow(false)
    val themeMode = _themeMode

    init {
        getDarkMode()
    }

    fun insertDarkMode(mode: Boolean) {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.editThemeMode(mode)
        }
    }

    private fun getDarkMode() {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.getThemeMode().collect { mode ->
                _themeMode.value = mode
            }
        }
    }
}