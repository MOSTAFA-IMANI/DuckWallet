package com.akaam.app.duckwallet.ui.features.notification

import androidx.compose.runtime.Immutable

@Immutable
sealed interface NotificationUiState {
    val isLoading: Boolean

    object Nothing : NotificationUiState {
        override val isLoading: Boolean
            get() = false
    }

}