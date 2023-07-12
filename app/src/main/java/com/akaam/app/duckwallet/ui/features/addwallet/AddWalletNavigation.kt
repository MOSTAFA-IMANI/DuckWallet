package com.akaam.app.duckwallet.ui.features.addwallet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val addWalletNavigationRoute = "add_wallet_route"

fun NavController.navigateToAddWallet(navOptions: NavOptions? = null){
    this.navigate(addWalletNavigationRoute, navOptions)
}

fun NavGraphBuilder.addWalletScreen(
    navigateToCreateWallet: () -> Unit,
    navigateToImportWallet: () -> Unit,
    navigateToPairLedger: () -> Unit,
    navigateToWatchWallet: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier
) {
    composable(addWalletNavigationRoute) {
        AddWalletRoute(
            navigateToCreateWallet =navigateToCreateWallet ,
             navigateToImportWallet = navigateToImportWallet,
             navigateToPairLedger = navigateToPairLedger,
             navigateToWatchWallet = navigateToWatchWallet,
            onFailureOccurred = onFailureOccurred,
            modifier =  modifier

        )
    }
}