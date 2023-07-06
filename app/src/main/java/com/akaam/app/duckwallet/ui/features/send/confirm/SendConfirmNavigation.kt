package com.akaam.app.duckwallet.ui.features.send.confirm

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val sendConfirmNavigationRoute = "send_confirm_route"
const val sendConfirmNavigationRouteLabel = "SEND"

fun NavController.navigateToSendConfirm(navOptions: NavOptions? = null): String {
    this.navigate(sendConfirmNavigationRoute, navOptions)
    return sendConfirmNavigationRouteLabel
}

fun NavGraphBuilder.sendConfirmScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
) {
    composable(sendConfirmNavigationRoute) {
        SendConfirmRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier
        )
    }
}