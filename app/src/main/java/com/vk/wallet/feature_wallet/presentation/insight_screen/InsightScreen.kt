package com.vk.wallet.feature_wallet.presentation.insight_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.R
import com.vk.wallet.feature_wallet.presentation.home_screen.components.ListPlaceholder
import com.vk.wallet.feature_wallet.presentation.insight_screen.components.DonutChart
import com.vk.wallet.feature_wallet.presentation.insight_screen.components.InsightItem
import com.vk.wallet.feature_wallet.presentation.insight_screen.components.InsightTabBar
import com.vk.wallet.feature_wallet.presentation.util.Categories
import com.vk.wallet.feature_wallet.presentation.util.amountFormat

@Composable
fun InsightScreen(insightViewModel: InsightViewModel = hiltViewModel()) {

    val filteredTransactions by insightViewModel.filteredTransaction.collectAsState()
    val currencyCode by insightViewModel.selectedCurrencyCode.collectAsState()

    val total = filteredTransactions.sumOf { it.amount }

    val groupedData = filteredTransactions.groupBy { it.category }

    val filteredCategories = mutableListOf<Categories>()

    groupedData.forEach { data ->
        Categories.entries.forEach cat@{
            if (data.key == it.title) {
                filteredCategories.add(it)
                return@cat
            }
        }
    }

    val amountByCategories = groupedData.map {
        it.value.sumOf { trx ->
            trx.amount
        }
    }

    val totalTransactions = amountByCategories.map { it.toFloat() }.sum()

    val percentProgress = amountByCategories.map {
        it.toFloat() * 100 / totalTransactions
    }

    var expandedState by remember { mutableStateOf(false) }

    val sortTimeFrame by remember {
        mutableStateOf(
            listOf(
                "Last 3 Days", "Last 7 Days", "Last 14 Days", "This Month",
                "Last Month", "All"
            )
        )
    }
    var selectedDuration by remember { mutableStateOf(sortTimeFrame.last()) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
            top = 4.dp,
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .clickable { expandedState = !expandedState }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedDuration,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    painter = painterResource(id = R.drawable.pop_up),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )

                DropdownMenu(
                    expanded = expandedState,
                    onDismissRequest = { expandedState = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                ) {
                    sortTimeFrame.forEachIndexed { index, label ->
                        DropdownMenuItem(
                            onClick = {
                                selectedDuration = label
                                insightViewModel.getFilteredTransaction(index)
                                expandedState = false
                            },
                            text = {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (selectedDuration == label)
                                        MaterialTheme.colorScheme.onPrimary
                                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                                )
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            InsightTabBar()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                letterSpacing = TextUnit(1.1f, TextUnitType.Sp),
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = currencyCode + total.toString().amountFormat(),
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 20.sp),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary
            )

            LazyColumn {
                item {
                    if (filteredTransactions.isNotEmpty())
                        DonutChart(filteredCategories, percentProgress)
                }

                itemsIndexed(filteredCategories) { index, category ->
                    val amount = groupedData[category.title]?.sumOf { it.amount }
                    InsightItem(cat = category, currencyCode, amount = amount!!, percentProgress[index])
                }
            }

            filteredTransactions.ifEmpty {
                ListPlaceholder(
                    "No transaction. Tap the '+' button on the home menu to get started."
                )
            }

        }
    }

}