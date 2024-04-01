package com.vk.wallet.feature_wallet.presentation.util

import androidx.compose.ui.graphics.Color
import com.vk.wallet.R
import com.vk.wallet.ui.theme.businessBg
import com.vk.wallet.ui.theme.clothBg
import com.vk.wallet.ui.theme.electricBg
import com.vk.wallet.ui.theme.food_drink
import com.vk.wallet.ui.theme.gadgetBg
import com.vk.wallet.ui.theme.giftBg
import com.vk.wallet.ui.theme.groceryBg
import com.vk.wallet.ui.theme.healthBg
import com.vk.wallet.ui.theme.homeBg
import com.vk.wallet.ui.theme.leisureBg
import com.vk.wallet.ui.theme.miscBg
import com.vk.wallet.ui.theme.petBg
import com.vk.wallet.ui.theme.salary
import com.vk.wallet.ui.theme.schBg
import com.vk.wallet.ui.theme.snackBg
import com.vk.wallet.ui.theme.subBg
import com.vk.wallet.ui.theme.taxiBg
import com.vk.wallet.ui.theme.travelBg
import com.vk.wallet.ui.theme.vehicleBg

enum class Categories(
    val title: String,
    val iconRes: Int,
    val bgRes: Color,
    val colorRes: Color = Color.White
) {
    SALARY("Salary", R.drawable.salary, salary, Color.Black),
    FOOD_DRINK("Food & Drink", R.drawable.drink, food_drink, Color.Black),
    CLOTHING("Clothing", R.drawable.clothing, clothBg, Color.Black),
    HOME("Home", R.drawable.home, homeBg, Color.Black),
    HEALTH("Health", R.drawable.health, healthBg),
    SCHOOL("School", R.drawable.school, schBg),
    GROCERY("Grocery", R.drawable.grocery, groceryBg, Color.Black),
    ELECTRICITY("Electricity", R.drawable.electricity, electricBg, Color.Black),
    BUSINESS("Business", R.drawable.business, businessBg, Color.Black),
    VEHICLE("Vehicle", R.drawable.vehicle, vehicleBg),
    TAXI("Uber & Taxi", R.drawable.uber, taxiBg),
    LEISURE("Leisure", R.drawable.leisure, leisureBg, Color.Black),
    GADGET("Gadget", R.drawable.gadget, gadgetBg),
    TRAVEL("Travel", R.drawable.travel, travelBg, Color.Black),
    SUBSCRIPTION("Subscription", R.drawable.subscriptions, subBg),
    PET("Pet", R.drawable.pet, petBg, Color.Black),
    SNACK("Snack", R.drawable.snack, snackBg, Color.Black),
    GIFT("Gift", R.drawable.gift, giftBg, Color.Black),
    MISCELLANEOUS("Miscellaneous", R.drawable.miscellaneous, miscBg)
}