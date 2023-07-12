package com.akaam.app.duckwallet.ui.features.addwallet

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AddWalletUiState {
    val isLoading: Boolean

    object Nothing : AddWalletUiState {
        override val isLoading: Boolean
            get() = false
    }

    object Loading : AddWalletUiState {
        override val isLoading: Boolean
            get() = true
    }


}