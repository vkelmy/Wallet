package com.vk.wallet.feature_wallet.presentation.navigation.components

import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.navigation.Screen

data class NavBarItemHolder(
    val title: String,
    val icon: Int,
    val route: String
)

fun provideBottomBavItems() = listOf(
    NavBarItemHolder(
        "",
        R.drawable.home,
        Screen.HomeScreen.route
    ),
    NavBarItemHolder(
        "",
        R.drawable.donut,
        Screen.InsightScreen.route
    ),
    NavBarItemHolder(
        "",
        R.drawable.account,
        Screen.AccountScreen.route
    ),
    NavBarItemHolder(
        "",
        R.drawable.settings,
        Screen.SettingScreen.route
    ),
)