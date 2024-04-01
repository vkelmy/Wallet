package com.vk.wallet.feature_wallet.presentation.setting_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.CurrencySetting
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.DarkMode
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.EraseContent
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.EraseSetting
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.LimitContent
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.LimitSetting
import com.vk.wallet.feature_wallet.presentation.setting_screen.components.ReminderSetting
import com.vk.wallet.feature_wallet.presentation.user_settings.DarkModeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
    darkModeViewModel: DarkModeViewModel = hiltViewModel(),
    navController: NavController
) {
    val currency by settingViewModel.currency.collectAsState()

    val theme by darkModeViewModel.themeMode.collectAsState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        SheetState(
            skipPartiallyExpanded = false,
            initialValue = SheetValue.Hidden,
            density = LocalDensity.current,
            skipHiddenState = false
        )
    )

    val scope = rememberCoroutineScope()

    val sheetRankState = remember {
        mutableIntStateOf(0)
    }

    BottomSheetScaffold(
        sheetContent = {
            Box(Modifier.defaultMinSize(minHeight = 1.dp)) {
                when (sheetRankState.intValue) {
                    1 -> {
                        LimitContent(bottomSheetScaffoldState, scope)
                    }
                    2 -> {
                        EraseContent(bottomSheetScaffoldState, scope)
                    }
                }
            }
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W700),
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 9.dp,
                                vertical = 6.dp
                            ),
                        textAlign = TextAlign.Start
                    )

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {

                        CurrencySetting(currency, navController)

                        LimitSetting(bottomSheetScaffoldState, scope) {
                            sheetRankState.intValue = it
                        }

                        ReminderSetting()

                        DarkMode(
                            darkTheme = theme,
                            onClick = { darkModeViewModel.insertDarkMode(!theme) }
                        )

                        EraseSetting(bottomSheetScaffoldState, scope) {
                            sheetRankState.intValue = it
                        }
                    }
                }
            }
        }
    }


}