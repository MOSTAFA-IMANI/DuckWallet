package com.akaam.app.duckwallet.ui.features.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable

const val homeNavigationRoute = "home_route/{source}"
const val homeNavigationRouteLabel = "MIMIC WALLET"
private const val TAG = "HomeNavigation"
fun NavController.navigateToHome(navOptions: NavOptions? = null,homeSourceNavigationOptions:HomeSourceNavigationOptions = HomeSourceNavigationOptions.Default): String {
    Log.d(TAG, "navigateToHome: ${homeSourceNavigationOptions::class.java.name}")
    this.navigate(homeNavigationRoute.replace("{source}",homeSourceNavigationOptions.source), navOptions)
    return homeNavigationRouteLabel
}

fun NavGraphBuilder.homeScreen(
    navigateToSendToken: () -> Unit,
    navigateToReceiveToken: () -> Unit,
    navigateToSwapToken: () -> Unit,
    navigateToStakeToken: () -> Unit,
    navigateToBuyToken: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    onMenuItemClick: (HomeMenuItem)->Unit
) {
    composable(homeNavigationRoute ) { navBackStackEntry ->
        val source = navBackStackEntry.arguments?.getString("source")
        val homeSourceNavigationOptions = HomeSourceNavigationOptions.from(source?:"")
        HomeRoute(
           navigateToSendToken = navigateToSendToken,
           navigateToReceiveToken = navigateToReceiveToken,
           navigateToSwapToken = navigateToSwapToken,
           navigateToStakeToken = navigateToStakeToken,
            navigateToBuyToken=navigateToBuyToken,
            onFailureOccurred = onFailureOccurred,
            onMenuItemClick = onMenuItemClick,
                    homeSourceNavigationOptions=homeSourceNavigationOptions

        )
    }
}