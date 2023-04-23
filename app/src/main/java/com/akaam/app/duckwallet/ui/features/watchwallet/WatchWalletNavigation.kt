package com.akaam.app.duckwallet.ui.features.watchwallet

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val watchWalletNavigationRoute = "watch_wallet_route"
const val watchWalletNavigationRouteLabel = "WATCH WALLET"

fun NavController.navigateToWatchWallet(
    navOptions: NavOptions? = null,
    destinationLabel: MutableState<String>
) {
    destinationLabel.value = watchWalletNavigationRouteLabel
    this.navigate(watchWalletNavigationRoute, navOptions)
}

fun NavGraphBuilder.watchWalletScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier
) {
    composable(watchWalletNavigationRoute) {
        WatchWalletRoute(
            navigateToHome = navigateToHome,
            modifier = modifier,
        )
    }
}