package com.vk.wallet.feature_wallet.presentation.home_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.home_screen.HomeViewModel
import com.vk.wallet.feature_wallet.presentation.util.Categories

@Composable
fun Category(
    expenseItems: Array<Categories> = Categories.entries.toTypedArray()
) {
    FlowRow(
        crossAxisSpacing = 4.dp,
        modifier = Modifier.padding(
            start = 8.dp,
            top = 8.dp,
            bottom = 8.dp,
        )
    ) {
        expenseItems.forEach {
            CategoryTag(category = it)
        }
    }
}

@Composable
fun CategoryTag(
    category: Categories,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val select by homeViewModel.category.collectAsState()
    var isSelected = select.title == category.title

    TextButton(
        modifier = Modifier.padding(end = 2.dp),
        onClick = {
            homeViewModel.selectCategory(category)
            isSelected = select.title == category.title
        },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 4.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                category.bgRes.copy(alpha = 0.95f)
            else MaterialTheme.colorScheme.primary,
            contentColor = if (isSelected)
                category.colorRes
            else MaterialTheme.colorScheme.onPrimary

        )
    ) {
        Icon(
            painter = if (!isSelected)
                painterResource(id = R.drawable.add_entry)
            else painterResource(id = category.iconRes),
            contentDescription = category.title,

        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = category.title,
            style = MaterialTheme.typography.labelLarge
        )
    }
}