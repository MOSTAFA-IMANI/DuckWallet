package com.akaam.app.duckwallet.ui.features.addwallet


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.AddressBook
import com.akaam.app.duckwallet.ui.theme.ChooseWalletDialog
import com.akaam.app.duckwallet.ui.theme.ClickableText
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold


@Composable
fun AddWalletRoute(
    navigateToCreateWallet: () -> Unit,
    navigateToImportWallet: () -> Unit,
    navigateToPairLedger: () -> Unit,
    navigateToWatchWallet: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddWalletViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val walletList by viewModel.walletList.collectAsStateWithLifecycle()

    WelcomeScreen(
        uiState = uiState,
        navigateToCreateWallet = navigateToCreateWallet,
        navigateToImportWallet = navigateToImportWallet,
        navigateToPairLedger = navigateToPairLedger,
        navigateToWatchWallet = navigateToWatchWallet,
        walletList = walletList,
        modifier = modifier
    )
}


@Composable
fun WelcomeScreen(
    uiState: AddWalletUiState,
    navigateToCreateWallet: () -> Unit,
    navigateToImportWallet: () -> Unit,
    navigateToPairLedger: () -> Unit,
    navigateToWatchWallet: () -> Unit,
    modifier: Modifier = Modifier,
    walletList: List<AddressBook>,
) {
    var changeWalletDialogShowingState by remember {
        mutableStateOf(false)
    }
    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_add_wallet).uppercase(),
        detailTopBarIcon = R.drawable.ic_add_wallet
    ) {

        if (changeWalletDialogShowingState) {
            val context = LocalContext.current
            ChooseWalletDialog(
                walletList = walletList,
                setShowDialog = {
                    changeWalletDialogShowingState = it
                },
                onConfirm = {
                    Toast.makeText(context, "Wallet Change, $it Selected ", Toast.LENGTH_SHORT)
                        .show()
                },
                onDismiss = {})
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            ClickableText(
                onClick = { changeWalletDialogShowingState = true },
                text = stringResource(id = R.string.change_wallet_button_title),

                )
            ClickableText(
                onClick = { navigateToCreateWallet() },
                text = stringResource(id = R.string.create_wallet_button_title),

                )
            ClickableText(
                onClick = { navigateToImportWallet() },
                text = stringResource(id = R.string.import_wallet_button_title),

                )

            ClickableText(
                onClick = { navigateToPairLedger() },
                text = stringResource(id = R.string.pair_ledger_button_title),

                )

            ClickableText(
                onClick = { navigateToWatchWallet() },
                text = stringResource(id = R.string.watch_wallet_button_title),

                )
        }
    }

    when (uiState) {
        AddWalletUiState.Loading -> {

        }

        AddWalletUiState.Nothing -> {

        }
    }
}



