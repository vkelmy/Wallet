package com.vk.wallet.feature_wallet.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.home_screen.components.AccountTag
import com.vk.wallet.feature_wallet.presentation.home_screen.components.Category
import com.vk.wallet.feature_wallet.presentation.home_screen.components.InfoBanner
import com.vk.wallet.feature_wallet.presentation.home_screen.components.Keypad
import com.vk.wallet.feature_wallet.presentation.util.AccountsType
import com.vk.wallet.feature_wallet.presentation.util.TransactionType
import com.vk.wallet.feature_wallet.presentation.util.amountFormat
import com.vk.wallet.ui.theme.Amber500
import com.vk.wallet.ui.theme.Red200
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    navController: NavController,
    transactionType: Int?,
    transactionDate: String?,
    transactionIndex: Int?,
    transactionSort: Int?,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val transactionType = TransactionType.entries[transactionType!!]
    val scope = rememberCoroutineScope()
    val keypadBottomSheetState = rememberBottomSheetScaffoldState(
        SheetState(
            skipPartiallyExpanded = false,
            initialValue = SheetValue.Hidden,
            density = LocalDensity.current,
            skipHiddenState = false
        )
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val title by remember { mutableStateOf(homeViewModel.transactionTitle) }
    val titleFieldValue = TextFieldValue(title.collectAsState().value)
    val showInfoBanner by homeViewModel.showInfoBanner.collectAsState()
    val expenseAmount by homeViewModel.transactionAmount.collectAsState()
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()
    val limitKey by homeViewModel.limitKey.collectAsState()
    val limitInfoWarning by homeViewModel.limitAlert.collectAsState(initial = HomeUIEvent.NoAlert())

    BottomSheetScaffold(
        sheetContent = {
            Keypad(
                bottomSheetScaffoldState = keypadBottomSheetState
            ) {
                homeViewModel.setTransaction(it)
            }
        },
        scaffoldState = keypadBottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background
    ) {
        LaunchedEffect(key1 = transactionIndex) {
            if (transactionIndex != -1) {
                homeViewModel.displayTransaction(transactionDate, transactionIndex, transactionSort)
            }
            homeViewModel.displayExpenseLimitWarning()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = it.calculateBottomPadding())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 4.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add Transaction",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    IconButton(
                        onClick = {
                            homeViewModel.apply {
                                if (transactionIndex == -1) {
                                    setCurrentTime(Calendar.getInstance().time)
                                    if (transactionType == TransactionType.INCOME) {
                                        insertDailyTransaction(
                                            date.value,
                                            transactionAmount.value.toDouble(),
                                            category.value.title,
                                            TransactionType.INCOME.title,
                                            transactionTitle.value
                                        ) {
                                            navController.navigateUp()
                                        }
                                    } else {
                                        insertDailyTransaction(
                                            date.value,
                                            transactionAmount.value.toDouble(),
                                            category.value.title,
                                            TransactionType.EXPENSE.title,
                                            transactionTitle.value
                                        ) {
                                            navController.navigateUp()
                                        }
                                    }
                                } else {
                                    updateTransaction(
                                        transactionDate,
                                        transactionIndex,
                                        transactionSort
                                    ) {
                                        navController.navigateUp()
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .scale(0.9f)
                            .background(MaterialTheme.colorScheme.secondary, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_entry),
                            contentDescription = "enter",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.scale(0.9f)
                        )
                    }

                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .scale(0.9f)
                            .border(1.dp, MaterialTheme.colorScheme.onPrimary, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "close",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.scale(0.9f)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InfoBanner(shown = showInfoBanner, transactionType)

                    OutlinedTextField(
                        value = titleFieldValue.text,
                        onValueChange = { field ->
                            homeViewModel.setTransactionTitle(field)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 9.dp,
                                top = 4.dp,
                                end = 9.dp,
                                bottom = 8.dp
                            ),
                        maxLines = 1,
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = if (transactionType == TransactionType.INCOME)
                                    "Income title"
                                else "Expense title",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                            )
                        },
                        textStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W600),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.onPrimary,
                            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                        )
                    )

                    Text(
                        text = if (transactionType == TransactionType.INCOME) {
                            "Fund"
                        } else "Pay with",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(
                                horizontal = 9.dp,
                                vertical = 4.dp
                            )
                            .align(Alignment.Start)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 5.dp
                            )
                            .align(Alignment.Start)
                            .fillMaxWidth()
                    ) {
                        items(AccountsType.entries.toTypedArray()) { account ->
                            AccountTag(account = account)
                        }
                    }
                    var isSheetOpen by rememberSaveable {
                        mutableStateOf(false)
                    }
                    TextButton(
                        onClick = {
                            scope.launch {
                                keyboardController?.hide()
                                if (isSheetOpen) {
                                    keypadBottomSheetState.bottomSheetState.hide()
                                    isSheetOpen = false
                                } else {
                                    isSheetOpen = true
                                    keypadBottomSheetState.bottomSheetState.expand()
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(
                                start = 8.dp,
                                top = 4.dp
                            ),
                        colors = ButtonDefaults.textButtonColors(Amber500.copy(alpha = 0.8f)),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 12.dp
                        )
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.W300,
                                        fontSize = 24.sp
                                    )
                                ) {
                                    append(currencyCode)
                                    append(expenseAmount.amountFormat())
                                }
                            },
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    if (limitKey) {
                        if (limitInfoWarning is HomeUIEvent.Alert) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 10.dp)
                                    .align(Alignment.Start)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.info_warning),
                                    contentDescription = null,
                                    tint = Red200
                                )
                                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)) {
                                    Text(
                                        text = (limitInfoWarning as HomeUIEvent.Alert).info,
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Set category",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        letterSpacing = TextUnit(0.2f, TextUnitType.Sp),
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(
                                horizontal = 9.dp,
                                vertical = 4.dp
                            )
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )

                    Category()
                }
            }
        }
    }
}