package com.vk.wallet.feature_wallet.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.navigation.Screen
import com.vk.wallet.ui.theme.expenseGradient
import com.vk.wallet.ui.theme.incomeGradient
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEntryChooser(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(
                onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    navController.navigate("${Screen.TransactionScreen.route}/0")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.income),
                    contentDescription = "add income",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .size(48.dp, 48.dp)
                        .background(incomeGradient, CircleShape)
                        .padding(8.dp)
                )
            }

            Text(
                text = "ADD INCOME",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    navController.navigate("${Screen.TransactionScreen.route}/1")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.expense),
                    contentDescription = "add expense",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .size(48.dp, 48.dp)
                        .background(expenseGradient, CircleShape)
                        .padding(8.dp)
                )
            }

            Text(
                text = "ADD EXPENSE",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}