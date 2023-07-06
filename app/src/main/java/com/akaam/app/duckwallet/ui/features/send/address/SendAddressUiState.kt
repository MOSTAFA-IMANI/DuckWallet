package com.akaam.app.duckwallet.ui.features.send.address

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SendAddressUiState {
    object Nothing : SendAddressUiState
}