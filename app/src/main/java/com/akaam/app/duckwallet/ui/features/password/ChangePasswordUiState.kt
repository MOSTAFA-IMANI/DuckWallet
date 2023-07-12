package com.akaam.app.duckwallet.ui.features.password

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ChangePasswordUiState {
    val isLoading: Boolean

    object Nothing : ChangePasswordUiState {
        override val isLoading: Boolean
            get() = false
    }

}