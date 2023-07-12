

package com.akaam.app.duckwallet.ui.features.welcome


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold


@Composable
fun WelcomeRoute(
    navigateToCreateWallet: () -> Unit,
    navigateToImportWallet: () -> Unit,
    navigateToPairLedger: () -> Unit,
    navigateToWatchWallet: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WelcomeScreen(
        uiState = uiState,
        navigateToCreateWallet =navigateToCreateWallet ,
        navigateToImportWallet = navigateToImportWallet,
        navigateToPairLedger = navigateToPairLedger,
        navigateToWatchWallet = navigateToWatchWallet,
        onFailureOccurred = onFailureOccurred,
        modifier = modifier
    )
}


@Composable
fun WelcomeScreen(
    uiState: WelcomeUiState,
    navigateToCreateWallet: () -> Unit,
    navigateToImportWallet: () -> Unit,
    navigateToPairLedger: () -> Unit,
    navigateToWatchWallet: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier = Modifier
) {
    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_welcome).uppercase(),

    ){

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(0.2f))
            Image(modifier = Modifier.fillMaxWidth().weight(0.4f),
                painter = painterResource(id = R.drawable.ic_duck_search), contentDescription ="" )

            MainButton(
                onClick = { navigateToCreateWallet()},
                text = stringResource(id =  R.string.create_wallet_button_title).uppercase(),
                description = stringResource(id =  R.string.create_wallet_button_description),)
            Spacer(Modifier.weight(0.05f))
            MainButton(
                onClick = { navigateToImportWallet() },
                text = stringResource(id =  R.string.import_wallet_button_title).uppercase(),
                description = stringResource(id =  R.string.import_wallet_button_description),)
            Spacer(Modifier.weight(0.05f))

            MainButton(
                onClick = { navigateToPairLedger() },
                isSecondory = true,
                text = stringResource(id =  R.string.pair_ledger_button_title).uppercase(),
                description = stringResource(id =  R.string.pair_ledger_button_description),)
            Spacer(Modifier.weight(0.05f))

            MainButton(
                onClick = { navigateToWatchWallet() },
                isSecondory = true,
                text = stringResource(id =  R.string.watch_wallet_button_title).uppercase(),
                description = stringResource(id =  R.string.watch_wallet_button_description),)
            Spacer(Modifier.weight(0.25f))
        }
    }

    when (uiState) {
        WelcomeUiState.Nothing, WelcomeUiState.Loading -> Unit
        WelcomeUiState.SUCCESS -> {

        }
        is WelcomeUiState.Failure -> {
            onFailureOccurred(uiState.exception)
        }
    }
}



