package com.vk.wallet.feature_wallet.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vk.wallet.feature_wallet.presentation.user_settings.DarkModeViewModel
import com.vk.wallet.feature_wallet.presentation.navigation.MainScreen
import com.vk.wallet.ui.theme.ExpenseTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val darkModeViewModel: DarkModeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode by darkModeViewModel.themeMode.collectAsState()
            ExpenseTrackerTheme(darkTheme = isDarkMode) {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val destination by mainViewModel.startDestination.collectAsState()
                    MainScreen(startDestination = destination)
                }
            }
        }
    }
}
