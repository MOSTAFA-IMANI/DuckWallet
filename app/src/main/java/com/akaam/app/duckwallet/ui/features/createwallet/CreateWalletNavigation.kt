package com.akaam.app.duckwallet.ui.features.createwallet

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val createWalletNavigationRoute = "create_wallet_route"
const val createWalletNavigationRouteLabel = "CREATE WALLET"

fun NavController.navigateToCreateWallet(
    navOptions: NavOptions? = null,
    destinationLabel: MutableState<String>
) {
    destinationLabel.value = createWalletNavigationRouteLabel
    this.navigate(createWalletNavigationRoute, navOptions)
}

fun NavGraphBuilder.createWalletScreen(
    navigateToYourMnemonicCode: () -> Unit,
    modifier: Modifier
) {
    composable(createWalletNavigationRoute) {
        CreateWalletRoute(
            navigateToYourMnemonicCode = navigateToYourMnemonicCode,
            modifier = modifier,
        )
    }
}