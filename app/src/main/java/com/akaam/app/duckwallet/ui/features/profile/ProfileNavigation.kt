package com.akaam.app.duckwallet.ui.features.profile

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val profileNavigationRoute = "profile_route"
const val profileNavigationRouteLabel = "PROFILE"

fun NavController.navigateToProfile(navOptions: NavOptions? = null): String {
    this.navigate(profileNavigationRoute, navOptions)
    return profileNavigationRouteLabel
}

fun NavGraphBuilder.profileScreen(
    navigateToWalletName : ()->Unit,
    navigateToChangeWallet : ()->Unit,
    navigateToChangePassword : ()->Unit,
    navigateToChangeAvatar : ()->Unit,
    navigateToLockedByDefault : ()->Unit,
    navigateToBackupPK : ()->Unit,
    navigateToBackupMnemonic : ()->Unit,
    navigateToDeleteWallet : ()->Unit,
    modifier: Modifier
) {
    composable(profileNavigationRoute) {
        ProfileRoute(
            navigateToWalletName= navigateToWalletName,
            navigateToChangeWallet= navigateToChangeWallet,
            navigateToChangePassword= navigateToChangePassword,
            navigateToChangeAvatar= navigateToChangeAvatar,
            navigateToLockedByDefault= navigateToLockedByDefault,
            navigateToBackupPK= navigateToBackupPK,
            navigateToBackupMnemonic= navigateToBackupMnemonic,
            navigateToDeleteWallet= navigateToDeleteWallet,
            modifier =  modifier

        )
    }
}