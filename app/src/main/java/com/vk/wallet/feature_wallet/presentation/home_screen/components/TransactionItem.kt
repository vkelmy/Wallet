package com.vk.wallet.feature_wallet.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.presentation.home_screen.HomeViewModel
import com.vk.wallet.feature_wallet.presentation.util.Categories
import com.vk.wallet.feature_wallet.presentation.util.TransactionType
import com.vk.wallet.feature_wallet.presentation.util.amountFormat
import com.vk.wallet.ui.theme.GreenAlpha700
import com.vk.wallet.ui.theme.Red500

@Composable
fun TransactionItem(
    transaction: Transaction,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onItemClick: () -> Unit
) {
    val category = getCategory(transaction.category)
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()

    Card(
        onClick = { onItemClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            category.bgRes,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(
                            vertical = 6.dp,
                            horizontal = 12.dp
                        ),
                    color = category.colorRes,
                    letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
                )

                Text(
                    text = transaction.account,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(
                            vertical = 6.dp,
                            horizontal = 12.dp
                        ),
                    color = MaterialTheme.colorScheme.onPrimary,
                    letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = category.iconRes),
                    contentDescription = "transaction icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(18.dp)
                )

                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    if (transaction.transactionTitle.isNotEmpty()) {
                        Text(
                            text = transaction.transactionTitle,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Spacer(modifier = Modifier.height(2.dp))
                    }

                    Text(
                        text = currencyCode + "${transaction.amount}".amountFormat(),
                        color = if (transaction.transactionType == TransactionType.INCOME.title)
                            GreenAlpha700
                        else Red500.copy(alpha = 0.75f),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

fun getCategory(title: String): Categories {
    var result: Categories = Categories.SALARY
    Categories.entries.forEach {
        if (it.title == title)
            result = it
    }
    return result
}

