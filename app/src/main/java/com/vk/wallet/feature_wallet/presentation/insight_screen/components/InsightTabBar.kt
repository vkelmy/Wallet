package com.vk.wallet.feature_wallet.presentation.insight_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.feature_wallet.presentation.insight_screen.InsightViewModel
import com.vk.wallet.feature_wallet.presentation.util.TransactionType
import com.vk.wallet.ui.theme.GreenAlpha700
import com.vk.wallet.ui.theme.Red500

@Composable
fun InsightTabBar(
    cornerRadius: Dp = 24.dp,
    insightViewModel: InsightViewModel = hiltViewModel()
) {
    val selectedTab by insightViewModel.tabButton.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            InsightBar(
                modifier = Modifier.weight(1f),
                cornerRadius = cornerRadius,
                onTabClick = {
                    insightViewModel.selectTabButton(TransactionType.INCOME)
                },
                title = "Income",
                backgroundColor = animateColorAsState(
                    targetValue = if (selectedTab == TransactionType.INCOME)
                        GreenAlpha700 else Color.Transparent, label = "Income background color"
                ).value,
                textColor = if (selectedTab == TransactionType.INCOME)
                    MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
            )

            InsightBar(
                modifier = Modifier.weight(1f),
                cornerRadius = cornerRadius,
                onTabClick = {
                    insightViewModel.selectTabButton(TransactionType.EXPENSE)
                },
                title = "Expense",
                backgroundColor = animateColorAsState(
                    targetValue = if (selectedTab == TransactionType.EXPENSE)
                        Red500 else Color.Transparent, label = "Expense background color"
                ).value,
                textColor = if (selectedTab == TransactionType.EXPENSE)
                    MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun InsightBar(
    modifier: Modifier,
    cornerRadius: Dp,
    onTabClick: () -> Unit,
    title: String,
    backgroundColor: Color,
    textColor: Color
) {
    TextButton(
        onClick = onTabClick,
        modifier = modifier
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(
                    horizontal = 4.dp,
                    vertical = 2.dp
                )
                .align(Alignment.CenterVertically)
        )
    }
}
