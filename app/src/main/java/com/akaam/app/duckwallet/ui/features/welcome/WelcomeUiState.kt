package com.akaam.app.duckwallet.ui.features.welcome

import androidx.compose.runtime.Immutable

@Immutable
sealed interface WelcomeUiState {
    val isLoading: Boolean

    object Nothing : WelcomeUiState {
        override val isLoading: Boolean
            get() = false
    }

    object Loading : WelcomeUiState {
        override val isLoading: Boolean
            get() = true
    }

    object SUCCESS : WelcomeUiState {
        override val isLoading: Boolean
            get() = false
    }

    data class Failure(val exception: Throwable) : WelcomeUiState {
        override val isLoading: Boolean
            get() = false
    }
}