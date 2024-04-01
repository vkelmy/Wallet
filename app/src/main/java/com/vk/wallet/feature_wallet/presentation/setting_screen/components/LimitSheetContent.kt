package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.setting_screen.SettingViewModel
import com.vk.wallet.feature_wallet.presentation.setting_screen.service.LimitResetWorker
import com.vk.wallet.ui.theme.LightBlue500
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LimitContent(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val millisecond = 86_400_000L
    val limitDuration = listOf(1 * millisecond, 7 * millisecond, 30 * millisecond)
    val limitDurationText by remember {
        mutableStateOf(
            listOf("Daily", "Weekly", "Monthly")
        )
    }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val expenseLimitAmount by settingViewModel.expenseLimit.collectAsState()
    val expenseLimitDuration by settingViewModel.expenseLimitDuration.collectAsState()
    var selectedLimit by remember { mutableStateOf(limitDurationText[expenseLimitDuration]) }
    var isAmountEmpty by remember { mutableStateOf(false) }
    var limitTextFieldValue by remember { mutableStateOf(TextFieldValue(String())) }
    var expandedState by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(Size.Zero) }

    val context = LocalContext.current
    var resetWorkRequest = PeriodicWorkRequestBuilder<LimitResetWorker>(
        limitDuration.first(),
        TimeUnit.MILLISECONDS,
        (limitDuration.first() * 0.95).toLong(),
        TimeUnit.MILLISECONDS
    ).build()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
    ) {
        Text(
            text = "Set limit",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(6.dp)
        )

        TextField(
            value = limitTextFieldValue,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { field ->
                isAmountEmpty = false
                limitTextFieldValue = field
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 5.dp,
                    bottom = 9.dp
                ),
            maxLines = 1,
            singleLine = true,
            placeholder = {
                Text(
                    text = if (expenseLimitAmount == 0.0) "Amount" else expenseLimitAmount.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            isError = isAmountEmpty,
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .clickable {
                    expandedState = !expandedState
                }
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                .padding(11.dp)
                .onGloballyPositioned {
                    size = it.size.toSize()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selectedLimit,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Icon(
                painter = painterResource(R.drawable.pop_up),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )

            DropdownMenu(
                expanded = expandedState,
                onDismissRequest = { expandedState = false },
                modifier = Modifier
                    .width(
                        with(LocalDensity.current) {
                            size.width.toDp()
                        }
                    )
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                limitDurationText.forEachIndexed { index, label ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        onClick = {
                            selectedLimit = label
                            expandedState = false
                            selectedIndex = index
                            resetWorkRequest = PeriodicWorkRequestBuilder<LimitResetWorker>(
                                limitDuration[index],
                                TimeUnit.MILLISECONDS,
                                (limitDuration[index] * 0.95).toLong(),
                                TimeUnit.MILLISECONDS
                            ).build()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

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
                    val amount = limitTextFieldValue.text
                    if (amount.isBlank())
                        isAmountEmpty = true
                    else {
                        isAmountEmpty = false
                        settingViewModel.editExpenseLimit(limitTextFieldValue.text.toDouble())
                        bottomSheetScaffoldState.bottomSheetState.hide()
                        settingViewModel.editLimitDuration(selectedIndex)
                        val workManager = WorkManager.getInstance(context)
                        workManager.enqueueUniquePeriodicWork(
                            "RESET",
                            ExistingPeriodicWorkPolicy.UPDATE,
                            resetWorkRequest
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue500,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Set",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}