package com.akaam.app.duckwallet.ui.features.search

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SearchUiState {
    val isLoading: Boolean

    object Nothing : SearchUiState {
        override val isLoading: Boolean
            get() = false
    }

}