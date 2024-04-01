package com.vk.wallet.feature_wallet.presentation.navigation

sealed class Screen(val route: String) {
    data object WelcomeScreen: Screen("welcome")
    data object CurrencyScreen: Screen("currency")
    data object HomeScreen: Screen("home")
    data object TransactionScreen: Screen("transaction")
    data object InsightScreen: Screen("insight")
    data object AccountScreen: Screen("account")
    data object AccountDetailScreen: Screen("detail")
    data object SettingScreen: Screen("setting")
}