package com.akaam.app.duckwallet.ui.features.swap.selection

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SwapSelectionUiState {
    object Nothing : SwapSelectionUiState
    object OnNextStepClicked : SwapSelectionUiState

}