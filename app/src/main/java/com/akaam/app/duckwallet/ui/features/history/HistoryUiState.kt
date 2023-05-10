package com.akaam.app.duckwallet.ui.features.history

import androidx.compose.runtime.Immutable

@Immutable
sealed interface HistoryUiState {
    val isLoading: Boolean

    object Nothing : HistoryUiState {
        override val isLoading: Boolean
            get() = false
    }

    object AllTabSelected : HistoryUiState {
        override val isLoading: Boolean
            get() = true
    }

    object SendTabSelected : HistoryUiState {
        override val isLoading: Boolean
            get() = false
    }
    object ReceiveTabSelected : HistoryUiState {
        override val isLoading: Boolean
            get() = false
    }
    object StakeTabSelected : HistoryUiState {
        override val isLoading: Boolean
            get() = false
    }
}