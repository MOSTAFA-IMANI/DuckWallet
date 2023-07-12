package com.akaam.app.duckwallet.ui.features.pairledger.adddevice

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AddNewDeviceUiState {
    val isLoading: Boolean

    object Nothing : AddNewDeviceUiState {
        override val isLoading: Boolean
            get() = false
    }

    object Loading : AddNewDeviceUiState {
        override val isLoading: Boolean
            get() = true
    }

    object NavigateToHome : AddNewDeviceUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NotSupportError : AddNewDeviceUiState {
        override val isLoading: Boolean
            get() = false
    }


}