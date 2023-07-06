package com.akaam.app.duckwallet.ui.features.send.address

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val sendAddressNavigationRoute = "send_address_route"
const val sendAddressNavigationRouteLabel = "SEND"

fun NavController.navigateToSendAddress(navOptions: NavOptions? = null): String {
    this.navigate(sendAddressNavigationRoute, navOptions)
    return sendAddressNavigationRouteLabel
}

fun NavGraphBuilder.sendAddressScreen(
    modifier: Modifier = Modifier,
    onNextStepClick :()->Unit,
    onAddressBookClick: () -> Unit,
    onMyAccountsClick: () -> Unit,
    onRecentClick: () -> Unit,
) {
    composable(sendAddressNavigationRoute) {
        SendAddressRoute(
            onNextStepClick=onNextStepClick,
        modifier = modifier,
            onAddressBookClick = onAddressBookClick,
            onMyAccountsClick = onMyAccountsClick,
            onRecentClick = onRecentClick,

        )
    }
}