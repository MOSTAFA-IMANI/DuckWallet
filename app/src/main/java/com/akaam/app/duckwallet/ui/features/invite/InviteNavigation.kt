package com.akaam.app.duckwallet.ui.features.invite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val inviteNavigationRoute = "invite_route"
const val inviteNavigationRouteLabel = "FRIEND INVITATION"

fun NavController.navigateToInvite(navOptions: NavOptions? = null): String {
    this.navigate(inviteNavigationRoute, navOptions)
    return inviteNavigationRouteLabel
}

fun NavGraphBuilder.inviteScreen() {
    composable(inviteNavigationRoute) {
        InviteRoute()
    }
}