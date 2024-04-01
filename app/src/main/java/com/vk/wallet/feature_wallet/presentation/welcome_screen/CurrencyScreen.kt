package com.vk.wallet.feature_wallet.presentation.welcome_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vk.wallet.feature_wallet.domain.model.CurrencyModel
import com.vk.wallet.feature_wallet.presentation.navigation.Screen
import com.vk.wallet.ui.theme.Manrope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CurrencyScreen(
    navController: NavController,
    setting: Boolean?,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {

    val currencies by welcomeViewModel.countryCurrencies
    var selectedCountry by remember { mutableStateOf(CurrencyModel()) }

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        SheetState(
            skipPartiallyExpanded = false,
            initialValue = SheetValue.Hidden,
            density = LocalDensity.current,
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        sheetContent = {
            ContinueButton(
                selectedCountry,
                setting,
                navController = navController,
                welcomeViewModel = welcomeViewModel
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Set Currency",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W600),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 4.dp,
                            bottom = 10.dp
                        ),
                    textAlign = TextAlign.Start
                )

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(bottom = it.calculateBottomPadding())
                ) {
                    currencies.forEach { (firstChar, list) ->
                        stickyHeader {
                            Surface(color = MaterialTheme.colorScheme.background) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(
                                            start = 9.dp,
                                            end = 8.dp,
                                            top = 8.dp,
                                            bottom = 7.dp
                                        )
                                ) {
                                    Text(
                                        text = firstChar.toString(),
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }

                        items(list) { currency ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        selectedCountry = if (selectedCountry != currency) {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                            currency
                                        } else {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                                            CurrencyModel()
                                        }
                                    }
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    )
                            ) {
                                TextButton(
                                    onClick = {
                                        selectedCountry = if (selectedCountry != currency) {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                            currency
                                        } else {
                                            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                                            CurrencyModel()
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (selectedCountry == currency)
                                            MaterialTheme.colorScheme.secondary
                                        else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
                                        contentColor = if (selectedCountry == currency)
                                            MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.onPrimary
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(20.dp)
                                ) {
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.W600,
                                                    fontFamily = Manrope,
                                                    fontSize = 14.sp
                                                )
                                            ) {
                                                append(currency.country.uppercase())
                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.Normal,
                                                    fontFamily = Manrope,
                                                    fontSize = 14.sp
                                                )
                                            ) {
                                                append(" (${currency.currencyCode})")
                                            }
                                        },
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContinueButton(
    currency: CurrencyModel,
    setting: Boolean?,
    navController: NavController,
    welcomeViewModel: WelcomeViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 25.dp
            )
    ) {
        Button(
            onClick = {
                welcomeViewModel.saveCurrency(currency.currencyCode)
                if (setting!!) {
                    navController.popBackStack()
                } else {
                    navController.popBackStack()
                    welcomeViewModel.saveOnBoardingState(completed = true)
                    welcomeViewModel.createAccounts()
                    navController.navigate(Screen.HomeScreen.route)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {
            Text(
                text = "Set",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}