package com.akaam.app.duckwallet.ui.features.verifymnemonic

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val verifyMnemonicCodeNavigationRoute = "verify_mnemonic_code_route"
const val verifyMnemonicCodeNavigationLabel = "VERIFY SECRET PHRASE"

fun NavController.navigateToVerifyMnemonicCode(
    navOptions: NavOptions? = null,
    destinationLabel: MutableState<String>
) {
    destinationLabel.value = verifyMnemonicCodeNavigationLabel
    this.navigate(verifyMnemonicCodeNavigationRoute, navOptions)
}

fun NavGraphBuilder.verifyMnemonicCodeScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier
) {
    composable(verifyMnemonicCodeNavigationRoute) {
        VerifyMnemonicCodeRoute(
            navigateToHome = navigateToHome,
            modifier = modifier,
        )
    }
}