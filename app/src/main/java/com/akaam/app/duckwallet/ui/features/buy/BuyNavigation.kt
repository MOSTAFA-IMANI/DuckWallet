package com.akaam.app.duckwallet.ui.features.buy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val buyNavigationRoute = "buy_route"
const val buyNavigationRouteLabel = "BUY"

fun NavController.navigateToBuy(navOptions: NavOptions? = null): String {
    this.navigate(buyNavigationRoute, navOptions)
    return buyNavigationRouteLabel
}

fun NavGraphBuilder.buyScreen(
    modifier: Modifier = Modifier
) {
    composable(buyNavigationRoute  ){
        BuyRoute(
            modifier =  modifier
        )
    }
}