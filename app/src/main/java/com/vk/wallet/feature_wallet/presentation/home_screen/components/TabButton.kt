package com.vk.wallet.feature_wallet.presentation.home_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
import com.vk.wallet.feature_wallet.presentation.home_screen.HomeViewModel
import com.vk.wallet.feature_wallet.presentation.util.TabButton

@Composable
fun TabButton(
    tabs: Array<TabButton> = TabButton.entries.toTypedArray(),
    cornerRadius: Dp = 24.dp,
    onButtonClick: () -> Unit = { },
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val selectedTab by homeViewModel.tabButton.collectAsState()

    Surface(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
            top = 4.dp,
        ),
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
            tabs.forEach { tab ->
                val backgroundColor by animateColorAsState(
                    if (selectedTab == tab) MaterialTheme.colorScheme.onPrimary
                    else Color.Transparent,
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing),
                    label = "tab button background"
                )

                val textColor by animateColorAsState(
                    if (selectedTab == tab) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onPrimary,
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing),
                    label = "tab button text color"
                )

                TextButton(
                    onClick = {
                        homeViewModel.selectTabButton(tab)
                        onButtonClick()
                    },
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = backgroundColor,
                        contentColor = textColor
                    )
                ) {
                    Text(
                        text = tab.title,
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
        }
    }
}