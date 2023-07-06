package com.akaam.app.duckwallet.ui.features.send.confirm

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SendConfirmUiState {
    object Nothing : SendConfirmUiState
}