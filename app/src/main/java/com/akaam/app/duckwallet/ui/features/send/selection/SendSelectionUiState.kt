package com.akaam.app.duckwallet.ui.features.send.selection

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SendSelectionUiState {
    object Nothing : SendSelectionUiState
}