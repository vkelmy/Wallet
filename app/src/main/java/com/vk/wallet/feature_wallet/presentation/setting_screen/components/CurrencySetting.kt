package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.navigation.Screen

@Composable
fun CurrencySetting(
    currency: String,
    navController: NavController
) {
    TextButton(
        onClick = {
            navController.navigate("${Screen.CurrencyScreen.route}/${true}")
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 9.dp,
                vertical = 5.dp
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 9.dp,
            vertical = 20.dp
        )
    ) {
        Text(
            text = "Currency",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start
        )

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)) {
            Text(
                text = currency,
                style = MaterialTheme.typography.titleMedium
            )

            Icon(
                painter = painterResource(R.drawable.edit),
                contentDescription = null,
                modifier = Modifier.then(Modifier.size(16.dp))
            )
        }
    }
}