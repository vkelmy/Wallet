package com.vk.wallet.feature_wallet.presentation.util

import java.text.DecimalFormat

fun String.amountFormat(): String {
    val amountFormatter = DecimalFormat("#,##0.00")
    return " " + amountFormatter.format(this.toDouble())
}