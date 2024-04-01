package com.vk.wallet.feature_wallet.presentation.welcome_screen.components

import androidx.annotation.DrawableRes
import com.vk.wallet.R

sealed class OnBoardingPage(
    @DrawableRes
    val icon: Int,
    val title: String,
    val description: String
) {
    data object FirstPage: OnBoardingPage(
        R.drawable.entry,
        "Add entries",
        "Keep track of your income and expenses"
    )

    data object SecondPage : OnBoardingPage(
        R.drawable.insight,
        "Check insights",
        "Detailed weekly and monthly charts based on your entries"
    )

    data object ThirdPage : OnBoardingPage(
        R.drawable.decision,
        "Make right decisions",
        "Control your money flow and stay on top of your game"
    )
}