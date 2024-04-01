package com.vk.wallet.feature_wallet.presentation.account_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.wallet.feature_wallet.domain.model.Account
import com.vk.wallet.feature_wallet.presentation.util.AccountsType
import com.vk.wallet.feature_wallet.presentation.util.amountFormat

@Composable
fun AccountItem(
    account: Account,
    currency: String,
    onItemClick: (String) -> Unit
) {
    Card(
        onClick = { onItemClick(account.account) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.17f)
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 8.dp,
                end = 15.dp,
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
        ) {
            Text(
                text = account.account,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            letterSpacing = 0.4.sp
                        )
                    ) {
                        append(currency)
                    }
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.ExtraLight,
                            fontSize = 24.sp,
                            letterSpacing = 0.25.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        append(account.balance.toString().amountFormat())
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            )
            val color = when (account.account) {
                AccountsType.CASH.title -> AccountsType.CASH.color
                AccountsType.PIX.title -> AccountsType.PIX.color
                else -> AccountsType.CARD.color
            }

            Surface(
                color = color,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.4.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                ) {
                                    append(currency)
                                }
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Thin,
                                        fontSize = 18.sp,
                                        letterSpacing = 0.25.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                ) {
                                    append(account.income.toString().amountFormat())
                                }
                            }
                        )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.4.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                ) {
                                    append(currency)
                                }
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Thin,
                                        fontSize = 18.sp,
                                        letterSpacing = 0.25.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                ) {
                                    append(account.expense.toString().amountFormat())
                                }
                            }
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 9.dp, vertical = 9.dp)
                    ) {
                        Text(
                            text = "Income",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Text(
                            text = "Expense",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}