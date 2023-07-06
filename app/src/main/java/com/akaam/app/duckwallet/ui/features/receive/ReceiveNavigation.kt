package com.akaam.app.duckwallet.ui.features.receive

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val receiveNavigationRoute = "receive_selection_route"
const val receiveNavigationRouteLabel = "RECEIVE"

fun NavController.navigateToReceive(navOptions: NavOptions? = null): String {
    this.navigate(receiveNavigationRoute, navOptions)
    return receiveNavigationRouteLabel
}

fun NavGraphBuilder.receiveScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
) {
    composable(receiveNavigationRoute) {
        ReceiveRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier
        )
    }
}