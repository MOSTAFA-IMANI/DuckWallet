package com.akaam.app.duckwallet.ui.features.walletname

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val walletNameNavigationRoute = "wallet_name_route"

fun NavController.navigateToWalletName(navOptions: NavOptions? = null) {
    this.navigate(walletNameNavigationRoute, navOptions)
    return
}

fun NavGraphBuilder.walletNameScreen(

) {
    composable(walletNameNavigationRoute) {
        WalletNameRoute()
    }
}