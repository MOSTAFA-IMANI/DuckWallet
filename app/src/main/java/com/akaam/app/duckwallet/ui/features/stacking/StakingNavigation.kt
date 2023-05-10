package com.akaam.app.duckwallet.ui.features.stacking

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val stakingNavigationRoute = "stacking_route"
const val stakingNavigationRouteLabel = "STAKING"

fun NavController.navigateToStaking(navOptions: NavOptions? = null): String {
    this.navigate(stakingNavigationRoute, navOptions)
    return stakingNavigationRouteLabel
}

fun NavGraphBuilder.stakingScreen(

) {
    composable(stakingNavigationRoute) {
        StakingRoute()
    }
}