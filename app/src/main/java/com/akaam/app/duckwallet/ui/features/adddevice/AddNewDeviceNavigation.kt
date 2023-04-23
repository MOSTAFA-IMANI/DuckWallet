package com.akaam.app.duckwallet.ui.features.adddevice

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val addNewDeviceNavigationRoute = "add_new_device_route"
const val addNewDeviceNavigationRouteLabel = "ADD NEW DEVICE"

fun NavController.navigateToAddNewDeviceScreen(navOptions: NavOptions? = null): String {
    this.navigate(addNewDeviceNavigationRoute, navOptions)
    return addNewDeviceNavigationRouteLabel
}

fun NavGraphBuilder.addNewDeviceScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable(addNewDeviceNavigationRoute) {
        AddNewDeviceRoute(
            navigateToAddNewDevice =navigateToHome ,
            modifier =  modifier

        )
    }
}