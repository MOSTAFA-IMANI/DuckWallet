package com.akaam.app.duckwallet.ui.features.swap.confirm

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val swapConfirmNavigationRoute = "swap_confirm_route"
const val swapConfirmNavigationRouteLabel = "SWAP"

fun NavController.navigateToSwapConfirm(navOptions: NavOptions? = null): String {
    this.navigate(swapConfirmNavigationRoute, navOptions)
    return swapConfirmNavigationRouteLabel
}

fun NavGraphBuilder.swapConfirmScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
) {
    composable(swapConfirmNavigationRoute) {
        SwapConfirmRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier
        )
    }
}