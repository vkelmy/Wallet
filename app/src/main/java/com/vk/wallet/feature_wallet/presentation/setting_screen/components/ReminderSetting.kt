package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.feature_wallet.presentation.setting_screen.SettingViewModel

@Composable
fun ReminderSetting(
    settingViewModel: SettingViewModel = hiltViewModel()
) {

    val reminderLimit by settingViewModel.reminderLimit.collectAsState()

    TextButton(
        onClick = {
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
            text = "Limit Reminder",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )

        Switch(
            modifier = Modifier.padding(end = 13.dp),
            switch = reminderLimit
        ) { switched ->
            settingViewModel.editLimitKey(enabled = switched)
        }
    }
}