

package com.akaam.app.duckwallet.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun SplashRoute(
    navigateToLogin: () -> Unit,
    navigateToWelcome: () -> Unit,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {
    SplashScreen(
        navigateToLogin = navigateToLogin,
        navigateToWelcome = navigateToWelcome,
        navigateToHome=navigateToHome,
        modifier = modifier
    )
}

@Composable
internal fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToWelcome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    when (state) {
        SplashUiState.Loading -> {

        }
        SplashUiState.NavigateToWelcome -> {
            navigateToWelcome()
        }
        SplashUiState.NavigateToLogin -> {
            navigateToLogin()
        }
        SplashUiState.NavigateToHome -> {
            navigateToHome.invoke()
        }
    }
}