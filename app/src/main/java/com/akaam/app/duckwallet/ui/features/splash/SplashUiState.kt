package com.akaam.app.duckwallet.ui.features.splash

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SplashUiState {
    object Loading : SplashUiState
    object NavigateToLogin : SplashUiState
    object NavigateToWelcome : SplashUiState
    object NavigateToHome : SplashUiState
}