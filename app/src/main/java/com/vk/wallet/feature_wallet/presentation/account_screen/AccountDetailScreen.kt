package com.vk.wallet.feature_wallet.presentation.account_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.feature_wallet.presentation.home_screen.components.ListPlaceholder
import com.vk.wallet.feature_wallet.presentation.home_screen.components.TransactionItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountDetailScreen(
    accountName: String?,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val transactions by accountViewModel.transactions.collectAsState()

    if (accountName != null) {
        accountViewModel.getTransaction(accountName)
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(
            top = 5.dp
        )
    ) {
        transactions.ifEmpty {
            ListPlaceholder()
        }

        LazyColumn(
            contentPadding = PaddingValues(
                start = 9.dp,
                top = 5.dp,
                end = 9.dp
            )
        ) {
            item {
                Text(
                    text = "Transactions",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Normal),
                )

                Text(
                    text = accountName!!,
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 20.sp, fontWeight = FontWeight.W700),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
                )
            }

            transactions.forEach { (date, allTrx) ->
                stickyHeader {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                5.dp
                            ),
                    ) {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                itemsIndexed(allTrx) { _, transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onItemClick = {}
                    )
                }
            }
        }
    }
}