package com.akaam.app.duckwallet.ui.features.adressbook

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AddressBookUiState {
    object Nothing : AddressBookUiState
}