package com.akaam.app.duckwallet.ui.features.swap.selection

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val swapSelectionNavigationRoute = "swap_selection_route"
const val swapSelectionNavigationRouteLabel = "SWAP"

fun NavController.navigateToSwapSelection(navOptions: NavOptions? = null): String {
    this.navigate(swapSelectionNavigationRoute, navOptions)
    return swapSelectionNavigationRouteLabel
}

fun NavGraphBuilder.swapSelectionScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
) {
    composable(swapSelectionNavigationRoute) {
        SwapSelectionRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier
        )
    }
}