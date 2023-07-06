package com.akaam.app.duckwallet.ui.features.stake.selection

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val stakeSelectionNavigationRoute = "Stake_selection_route"
const val stakeSelectionNavigationRouteLabel = "STAKE"

fun NavController.navigateToStakeSelection(navOptions: NavOptions? = null): String {
    this.navigate(stakeSelectionNavigationRoute, navOptions)
    return stakeSelectionNavigationRouteLabel
}

fun NavGraphBuilder.stakeSelectionScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
) {
    composable(stakeSelectionNavigationRoute) {
        StakeSelectionRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier,


        )
    }
}