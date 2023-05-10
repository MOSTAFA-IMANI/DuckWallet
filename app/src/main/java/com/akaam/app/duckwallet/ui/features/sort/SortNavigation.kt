package com.akaam.app.duckwallet.ui.features.sort

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val sortByNavigationRoute = "sort_by_route"
const val sortByNavigationRouteLabel = "SORT BY"

fun NavController.navigateToSortBy(navOptions: NavOptions? = null): String {
    this.navigate(sortByNavigationRoute, navOptions)
    return sortByNavigationRouteLabel
}

fun NavGraphBuilder.sortByScreen(
    sortByNameOnClick : ()->Unit,
    sortByVolumeOnClick : ()->Unit,
    sortByDateOnClick : ()->Unit,
    modifier: Modifier
) {
    composable(sortByNavigationRoute) {
        SortRoute(
            sortByNameOnClick = sortByNameOnClick,
            sortByVolumeOnClick = sortByVolumeOnClick,
            sortByDateOnClick = sortByDateOnClick,
            modifier =  modifier

        )
    }
}