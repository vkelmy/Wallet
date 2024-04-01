package com.vk.wallet.feature_wallet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vk.wallet.feature_wallet.presentation.account_screen.AccountDetailScreen
import com.vk.wallet.feature_wallet.presentation.account_screen.AccountScreen
import com.vk.wallet.feature_wallet.presentation.home_screen.HomeScreen
import com.vk.wallet.feature_wallet.presentation.home_screen.TransactionScreen
import com.vk.wallet.feature_wallet.presentation.insight_screen.InsightScreen
import com.vk.wallet.feature_wallet.presentation.setting_screen.SettingScreen
import com.vk.wallet.feature_wallet.presentation.welcome_screen.CurrencyScreen
import com.vk.wallet.feature_wallet.presentation.welcome_screen.WelcomeScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = "${Screen.CurrencyScreen.route}/{setting}",
            arguments = listOf(
                navArgument("setting") {
                    type = NavType.BoolType
                    defaultValue = true
                }
            )
        ) { entry ->
            CurrencyScreen(
                navController = navController,
                setting = entry.arguments?.getBoolean("setting")
            )
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = "${Screen.TransactionScreen.route}/{trxType}?trxDate={trxDate}&trxIndex={trxIndex}&trxSort={trxSort}",
            arguments = listOf(
                navArgument("trxType") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("trxDate") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                },
                navArgument("trxIndex") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("trxSort") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            TransactionScreen(
                navController = navController,
                transactionType = entry.arguments?.getInt("trxType"),
                transactionDate = entry.arguments?.getString("trxDate"),
                transactionIndex = entry.arguments?.getInt("trxIndex"),
                transactionSort = entry.arguments?.getInt("trxSort")
            )
        }
        composable(route = Screen.InsightScreen.route) {
            InsightScreen()
        }
        composable(route = Screen.AccountScreen.route) {
            AccountScreen(navController = navController)
        }
        composable(
            route = "${Screen.AccountDetailScreen.route}/{accountType}",
            arguments = listOf(
                navArgument("accountType") {
                    type = NavType.StringType
                    defaultValue = "Cash"
                    nullable = true
                })
        ) { entry ->
            AccountDetailScreen(entry.arguments?.getString("accountType"))
        }
        composable(route = Screen.SettingScreen.route) {
            SettingScreen(navController = navController)
        }
    }
}