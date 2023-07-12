package com.akaam.app.duckwallet.ui.features.createwallet.mnemonic

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val mnemonicCodeNavigationRoute = "mnemonic_code_route"
const val mnemonicCodeNavigationLabel = "YOUR MNEMONIC CODE"

fun NavController.navigateToMnemonicCode(
    navOptions: NavOptions? = null,
    destinationLabel: MutableState<String>
) {
    destinationLabel.value = mnemonicCodeNavigationLabel
    this.navigate(mnemonicCodeNavigationRoute, navOptions)
}

fun NavGraphBuilder.mnemonicCodeScreen(
    navigateToVerifyMnemonicCode: () -> Unit,
    modifier: Modifier
) {
    composable(mnemonicCodeNavigationRoute) {
        MnemonicCodeRoute(
            navigateToVerifyMnemonicCode = navigateToVerifyMnemonicCode,
            modifier = modifier,
        )
    }
}