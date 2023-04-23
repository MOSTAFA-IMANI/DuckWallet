package com.akaam.app.duckwallet.ui.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val loginNavigationRoute = "login_route"

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null,
): String {
    this.navigate(loginNavigationRoute, navOptions)
    return loginNavigationRoute
}

fun NavGraphBuilder.loginScreen(
    navigateToWelcome: () -> Unit,
    navigateToRegister: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit
) {
    composable(loginNavigationRoute) {
        LoginRoute(
            navigateToWelcome = navigateToWelcome,
            navigateToRegister = navigateToRegister,
            onFailureOccurred = onFailureOccurred
        )
    }
}