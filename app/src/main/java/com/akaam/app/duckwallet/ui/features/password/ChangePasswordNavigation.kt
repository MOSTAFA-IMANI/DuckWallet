package com.akaam.app.duckwallet.ui.features.password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val changePasswordNavigationRoute = "change_password_route"

fun NavController.navigateToChangePassword(navOptions: NavOptions? = null) {
    this.navigate(changePasswordNavigationRoute, navOptions)
    return
}

fun NavGraphBuilder.changePasswordScreen(

) {
    composable(changePasswordNavigationRoute) {
        ChangePasswordRoute()
    }
}