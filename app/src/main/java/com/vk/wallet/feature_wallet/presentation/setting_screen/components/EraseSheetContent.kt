package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.feature_wallet.presentation.setting_screen.SettingViewModel
import com.vk.wallet.ui.theme.Red500
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EraseContent(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(11.dp)
    ) {
        val context = LocalContext.current
        Text(
            text = "Erase data",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = "You're about to erase all transactions on this app. This cannot be reversed",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(top = 11.dp, bottom = 11.dp)
        )

        TextButton(
            onClick = {
                scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        TextButton(
            onClick = {
                scope.launch {
                    settingViewModel.eraseTransaction()
                    bottomSheetScaffoldState.bottomSheetState.hide()
                    Toast.makeText(context, "Erased!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Red500,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}