package com.akaam.app.duckwallet.ui.features.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val historyNavigationRoute = "history_route"
const val historyNavigationRouteLabel = "HISTORY TRANSACTION"

fun NavController.navigateToHistory(navOptions: NavOptions? = null): String {
    this.navigate(historyNavigationRoute, navOptions)
    return historyNavigationRouteLabel
}

fun NavGraphBuilder.historyScreen(

) {
    composable(historyNavigationRoute) {
        HistoryRoute()
    }
}