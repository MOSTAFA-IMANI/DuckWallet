package com.akaam.app.duckwallet.ui.features.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
    return
}

fun NavGraphBuilder.searchScreen(

) {
    composable(searchNavigationRoute) {
        SearchRoute()
    }
}