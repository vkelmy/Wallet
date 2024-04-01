package com.vk.wallet.feature_wallet.presentation.home_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.home_screen.HomeViewModel
import com.vk.wallet.feature_wallet.presentation.util.AccountsType

@Composable
fun AccountTag(
    account: AccountsType,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val selectedAccount by homeViewModel.account.collectAsState()
    val isSelected = selectedAccount == account

    TextButton(
        onClick = { homeViewModel.selectAccount(account) },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 15.dp,
            vertical = 5.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
            else Color.Transparent,
            contentColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary
        )
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected) R.drawable.checked else account.iconRes
            ),
            contentDescription = account.title
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = account.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
        )
    }
}