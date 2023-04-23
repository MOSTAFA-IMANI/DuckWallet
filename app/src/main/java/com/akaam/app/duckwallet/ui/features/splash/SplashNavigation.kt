package com.akaam.app.duckwallet.ui.features.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val splashNavigationRoute = "splash_route"

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(splashNavigationRoute, navOptions)
}

fun NavGraphBuilder.splashScreen(
    navigateToLogin: () -> Unit,
    navigateToWelcome: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable(splashNavigationRoute) {
        SplashRoute(
            navigateToLogin = navigateToLogin,
            navigateToWelcome = navigateToWelcome,
            navigateToHome= navigateToHome
        )
    }
}