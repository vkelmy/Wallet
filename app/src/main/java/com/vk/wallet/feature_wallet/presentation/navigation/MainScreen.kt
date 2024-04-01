package com.vk.wallet.feature_wallet.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vk.wallet.feature_wallet.presentation.navigation.components.BottomNavBar
import com.vk.wallet.feature_wallet.presentation.navigation.components.provideBottomBavItems

@Composable
fun MainScreen(startDestination: String) {

    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    val rootDestinations = listOf(
        Screen.HomeScreen.route,
        Screen.InsightScreen.route,
        Screen.AccountScreen.route,
        Screen.SettingScreen.route
    )

    val bottomNavBarState = remember { mutableStateOf(true) }
    val navBarEntry by navController.currentBackStackEntryAsState()
    bottomNavBarState.value = rootDestinations.contains(navBarEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomNavBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavBar(
                    items = provideBottomBavItems(),
                    navController
                ) {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { values ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(
                bottom = values.calculateBottomPadding()
            )
        ) {
            MainNavigation(
                navController = navController,
                startDestination = startDestination
            )
        }
    }

}