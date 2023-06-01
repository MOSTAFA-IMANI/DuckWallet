package com.akaam.app.duckwallet.ui.features.swap.confirm

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SwapConfirmUiState {
    object Nothing : SwapConfirmUiState
}