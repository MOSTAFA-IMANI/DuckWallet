package com.akaam.app.duckwallet.ui.features.stake.selection

import androidx.compose.runtime.Immutable

@Immutable
sealed interface StakeSelectionUiState {
    object Nothing : StakeSelectionUiState
}