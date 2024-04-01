package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.feature_wallet.presentation.setting_screen.SettingViewModel
import com.vk.wallet.feature_wallet.presentation.util.amountFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LimitSetting(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    settingViewModel: SettingViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val currencyCode by settingViewModel.currency.collectAsState()
    val expenseLimit by settingViewModel.expenseLimit.collectAsState()


    TextButton(
        onClick = {
            onItemClick(1)
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
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
            text = "Expense Limit",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start
        )

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)) {
            Text(
                text = "$currencyCode " + expenseLimit.toString().amountFormat(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}