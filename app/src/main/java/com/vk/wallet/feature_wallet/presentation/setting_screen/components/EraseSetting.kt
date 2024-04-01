package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vk.wallet.R
import com.vk.wallet.ui.theme.Red500
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EraseSetting(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    onItemClick: (Int) -> Unit
) {

    TextButton(
        onClick = {
            scope.launch {
                onItemClick(2)
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
            containerColor = Red500,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 9.dp,
            vertical = 20.dp
        )
    ) {
        Text(
            text = "Erase Data",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start
        )

        Icon(
            painter = painterResource(R.drawable.edit),
            contentDescription = null,
            modifier = Modifier.then(Modifier.size(16.dp))
        )
    }
}