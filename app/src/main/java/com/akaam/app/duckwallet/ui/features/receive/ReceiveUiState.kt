package com.akaam.app.duckwallet.ui.features.receive

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ReceiveUiState {
    object Nothing : ReceiveUiState
    object OnConfirmClicked : ReceiveUiState

}