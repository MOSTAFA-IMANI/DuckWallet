package com.akaam.app.duckwallet.ui.features.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val welcomeNavigationRoute = "welcome_route"
const val welcomeNavigationRouteLabel = "WELCOME"

fun NavController.navigateToWelcome(navOptions: NavOptions? = null): String {
    this.navigate(welcomeNavigationRoute, navOptions)
    return welcomeNavigationRouteLabel
}

fun NavGraphBuilder.welcomeScreen(
    navigateToCreateWallet: () -> Unit,
    navigateToImportWallet: () -> Unit,
    navigateToPairLedger: () -> Unit,
    navigateToWatchWallet: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier
) {
    composable(welcomeNavigationRoute) {
        WelcomeRoute(
            navigateToCreateWallet =navigateToCreateWallet ,
             navigateToImportWallet = navigateToImportWallet,
             navigateToPairLedger = navigateToPairLedger,
             navigateToWatchWallet = navigateToWatchWallet,
            onFailureOccurred = onFailureOccurred,
            modifier =  modifier

        )
    }
}