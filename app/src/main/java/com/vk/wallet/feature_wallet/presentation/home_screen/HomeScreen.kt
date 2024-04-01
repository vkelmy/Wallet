package com.vk.wallet.feature_wallet.presentation.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vk.wallet.feature_wallet.presentation.home_screen.components.AddEntryChooser
import com.vk.wallet.feature_wallet.presentation.home_screen.components.Header
import com.vk.wallet.feature_wallet.presentation.home_screen.components.ListPlaceholder
import com.vk.wallet.feature_wallet.presentation.home_screen.components.TabButton
import com.vk.wallet.feature_wallet.presentation.home_screen.components.TransactionItem
import com.vk.wallet.feature_wallet.presentation.navigation.Screen
import com.vk.wallet.feature_wallet.presentation.util.TabButton
import com.vk.wallet.feature_wallet.presentation.util.TransactionType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val dailyTransactions by homeViewModel.dailyTransaction.collectAsState()
    val monthlyTransactions by homeViewModel.monthlyTransaction.collectAsState()
    val currentTabButton by homeViewModel.tabButton.collectAsState()

    val lazyListState = rememberLazyListState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        SheetState(
            skipPartiallyExpanded = false,
            initialValue = SheetValue.Hidden,
            density = LocalDensity.current,
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        sheetContent = { AddEntryChooser(bottomSheetScaffoldState, navController) },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(bottomSheetScaffoldState)

            TabButton()

            AnimatedVisibility(visible = currentTabButton == TabButton.TODAY) {
                dailyTransactions.ifEmpty {
                    ListPlaceholder()
                }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        top = 4.dp,
                        end = 8.dp,
                    )
                ) {
                    itemsIndexed(dailyTransactions) { index, dailyTransaction ->
                        TransactionItem(
                            transaction = dailyTransaction,
                            onItemClick = {
                                val trxType = dailyTransaction.transactionType
                                if (trxType == TransactionType.INCOME.title)
                                    navController.navigate("${Screen.TransactionScreen.route}/0?trxIndex=${index}&trxSort=${0}")
                                else
                                    navController.navigate("${Screen.TransactionScreen.route}/1?trxIndex=${index}&trxSort=${0}")
                            }
                        )
                    }
                }
            }



            AnimatedVisibility(visible = currentTabButton == TabButton.MONTH) {
                monthlyTransactions.ifEmpty {
                    ListPlaceholder()
                }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        top = 4.dp,
                        end = 8.dp,
                    )
                ) {
                    monthlyTransactions.forEach { (date, monthlyTransaction) ->
                        stickyHeader {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    )
                            ) {
                                Text(
                                    text = date,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }

                        itemsIndexed(monthlyTransaction) { index, transaction ->
                            TransactionItem(
                                transaction = transaction,
                                onItemClick = {
                                    val trxType = transaction.transactionType
                                    if (trxType == TransactionType.INCOME.title)
                                        navController.navigate("${Screen.TransactionScreen.route}/0?trxDate=${date}&trxIndex=${index}&trxSort=${1}")
                                    else
                                        navController.navigate("${Screen.TransactionScreen.route}/1?trxDate=${date}&trxIndex=${index}&trxSort=${1}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}