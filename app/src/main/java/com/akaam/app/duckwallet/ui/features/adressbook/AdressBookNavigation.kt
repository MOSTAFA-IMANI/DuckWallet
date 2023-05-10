package com.akaam.app.duckwallet.ui.features.adressbook

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val addressBookNavigationRoute = "address_book_route"
const val addressBookNavigationRouteLabel = "ADDRESS BOOK"

fun NavController.navigateToAddressBook(navOptions: NavOptions? = null): String {
    this.navigate(addressBookNavigationRoute, navOptions)
    return addressBookNavigationRouteLabel
}

fun NavGraphBuilder.addressBookScreen(
    modifier: Modifier = Modifier
) {
    composable(addressBookNavigationRoute ){
        AddressBookRoute(
            modifier =  modifier
        )
    }
}