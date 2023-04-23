package com.akaam.app.duckwallet.ui.features.login

import androidx.compose.runtime.Immutable

@Immutable
sealed interface LoginUiState {
    val isLoading: Boolean

    object Nothing : LoginUiState {
        override val isLoading: Boolean
            get() = false
    }

    object Loading : LoginUiState {
        override val isLoading: Boolean
            get() = true
    }

    object SUCCESS : LoginUiState {
        override val isLoading: Boolean
            get() = false
    }

    data class Failure(val exception: Throwable) : LoginUiState {
        override val isLoading: Boolean
            get() = false
    }
}