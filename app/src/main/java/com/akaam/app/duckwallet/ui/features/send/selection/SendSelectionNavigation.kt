package com.akaam.app.duckwallet.ui.features.send.selection

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val sendSelectionNavigationRoute = "send_selection_route"
const val sendSelectionNavigationRouteLabel = "SEND"

fun NavController.navigateToSendSelection(navOptions: NavOptions? = null): String {
    this.navigate(sendSelectionNavigationRoute, navOptions)
    return sendSelectionNavigationRouteLabel
}

fun NavGraphBuilder.sendSelectionScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
) {
    composable(sendSelectionNavigationRoute) {
        SendSelectionRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier,


        )
    }
}