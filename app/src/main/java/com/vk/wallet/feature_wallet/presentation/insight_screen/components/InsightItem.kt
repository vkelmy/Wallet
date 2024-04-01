package com.vk.wallet.feature_wallet.presentation.insight_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vk.wallet.feature_wallet.presentation.util.Categories
import com.vk.wallet.feature_wallet.presentation.util.amountFormat

@Composable
fun InsightItem(
    cat: Categories,
    currencyCode: String,
    amount: Double,
    percent: Float
) {
    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = cat.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .background(cat.bgRes, shape = CircleShape)
                    .padding(16.dp),
                tint = cat.colorRes
            )

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
            ) {
                Text(
                    text = cat.title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = currencyCode + "$amount".amountFormat(),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) {
                Text(
                    text = "${String.format("%.2f", percent)}%",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}