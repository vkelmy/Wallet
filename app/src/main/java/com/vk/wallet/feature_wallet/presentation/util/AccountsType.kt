package com.vk.wallet.feature_wallet.presentation.util

import androidx.compose.ui.graphics.Color
import com.vk.wallet.R
import com.vk.wallet.ui.theme.groceryBg
import com.vk.wallet.ui.theme.healthBg
import com.vk.wallet.ui.theme.leisureBg

enum class AccountsType(val title: String, val iconRes: Int, val color: Color) {
    CASH("Cash", R.drawable.cash, leisureBg),
    PIX("Pix", R.drawable.pix, groceryBg),
    CARD("Card", R.drawable.credit_card, healthBg)
}