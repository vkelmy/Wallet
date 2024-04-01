package com.vk.wallet.feature_wallet.presentation.welcome_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.vk.wallet.feature_wallet.presentation.navigation.Screen
import com.vk.wallet.feature_wallet.presentation.welcome_screen.components.GetStartedButton
import com.vk.wallet.feature_wallet.presentation.welcome_screen.components.PagerScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages by welcomeViewModel.listOfPages
    val pagerState = rememberPagerState()

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(10f)
            ) {pageCount ->
                PagerScreen(page = pages[pageCount])
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f),
                indicatorWidth = 18.dp,
                indicatorHeight = 4.dp,
                activeColor = MaterialTheme.colorScheme.secondary,
                inactiveColor = Color.LightGray
            )
            GetStartedButton(modifier = Modifier.weight(2f), pagerState = pagerState) {
                navController.popBackStack()
                navController.navigate("${Screen.CurrencyScreen.route}/${false}")
            }
        }
    }
}