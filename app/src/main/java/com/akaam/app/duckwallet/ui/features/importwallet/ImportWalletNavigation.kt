package com.akaam.app.duckwallet.ui.features.importwallet

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val importWalletNavigationRoute = "import_wallet_route"
const val importWalletNavigationRouteLabel = "IMPORT WALLET"

fun NavController.navigateToImportWallet(
    navOptions: NavOptions? = null,
    destinationLabel: MutableState<String>
) {
    destinationLabel.value = importWalletNavigationRouteLabel
    this.navigate(importWalletNavigationRoute, navOptions)
}

fun NavGraphBuilder.importWalletScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier
) {
    composable(importWalletNavigationRoute) {
        ImportWalletRoute(
            navigateToHome = navigateToHome,
            modifier = modifier,
        )
    }
}