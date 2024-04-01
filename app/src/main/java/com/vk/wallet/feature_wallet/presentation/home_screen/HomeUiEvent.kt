package com.vk.wallet.feature_wallet.presentation.home_screen

sealed class HomeUIEvent {
    data class Alert(val info: String) : HomeUIEvent()
    data class NoAlert(val info: String = String()) : HomeUIEvent()
}