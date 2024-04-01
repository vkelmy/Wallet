package com.vk.wallet.feature_wallet.presentation.setting_screen.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun DarkMode(
    darkTheme: Boolean = false,
    size: Dp = 40.dp,
    iconSize: Dp = size / 1.5f,
    padding: Dp = 5.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {

    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec, label = "offset"
    )

    TextButton(
        onClick = {
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 9.dp,
                vertical = 5.dp
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 9.dp,
            vertical = 6.dp
        )
    ) {

        Text(
            text = "Dark Mode",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )

        Box(modifier = Modifier
            .width(size * 2)
            .height(size)
            .clip(shape = parentShape)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
        ) {

            Box(
                modifier = Modifier
                    .size(size)
                    .offset(x = offset)
                    .padding(all = padding)
                    .clip(shape = toggleShape)
                    .background(Color.White)
            )
            Row {
                Box(
                    modifier = Modifier.size(size),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        imageVector = Icons.Default.Nightlight,
                        contentDescription = "Theme Icon",
                        tint = if (darkTheme) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.primaryContainer
                    )
                }
                Box(
                    modifier = Modifier.size(size),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        imageVector = Icons.Default.LightMode,
                        contentDescription = "Theme Icon",
                        tint = if (darkTheme) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}