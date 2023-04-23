package com.akaam.app.duckwallet.ui.features.pairledger

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val pairLedgerNavigationRoute = "pair_ledger_route"
const val pairLedgerNavigationRouteLabel = "PAIR LEDGER"

fun NavController.navigateToPairLedgerScreen(navOptions: NavOptions? = null): String {
    this.navigate(pairLedgerNavigationRoute, navOptions)
    return pairLedgerNavigationRouteLabel
}

fun NavGraphBuilder.pairLedgerScreen(
    navigateToAddNewDevice: () -> Unit,

    modifier: Modifier = Modifier
) {
    composable(pairLedgerNavigationRoute) {
        PairLedgerRoute(
            navigateToAddNewDevice =navigateToAddNewDevice ,
            modifier =  modifier

        )
    }
}