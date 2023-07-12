package com.akaam.app.duckwallet.ui.features.walletname

import androidx.compose.runtime.Immutable

@Immutable
sealed interface WalletNameUiState {
    val isLoading: Boolean

    object Nothing : WalletNameUiState {
        override val isLoading: Boolean
            get() = false
    }

}