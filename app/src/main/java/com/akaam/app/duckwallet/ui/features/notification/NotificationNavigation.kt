package com.akaam.app.duckwallet.ui.features.notification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val notificationNavigationRoute = "notification_route"

fun NavController.navigateToNotification(navOptions: NavOptions? = null) {
    this.navigate(notificationNavigationRoute, navOptions)
    return
}

fun NavGraphBuilder.notificationScreen(

) {
    composable(notificationNavigationRoute) {
        NotificationRoute()
    }
}