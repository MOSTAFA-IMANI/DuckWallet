package com.akaam.app.duckwallet.ui.features.buy

import androidx.compose.runtime.Immutable

@Immutable
sealed interface BuyUiState {
    object Nothing : BuyUiState
}