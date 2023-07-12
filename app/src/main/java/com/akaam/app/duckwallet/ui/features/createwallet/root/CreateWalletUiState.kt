package com.akaam.app.duckwallet.ui.features.createwallet.root

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CreateWalletUiState {
    val isLoading: Boolean

    object Nothing : CreateWalletUiState {
        override val isLoading: Boolean
            get() = false
    }  object Loading : CreateWalletUiState {
        override val isLoading: Boolean
            get() = true
    }
    data class PassWordVerifyFailure(val message: String?=null): CreateWalletUiState {
        override val isLoading: Boolean
            get() = false
    }

    data class WeakPassWordFailure(val message: String?=null): CreateWalletUiState {
        override val isLoading: Boolean
            get() = false
    }
    data class EmptyInputFailure(val message: String?=null): CreateWalletUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NavigateToYourMnemonicCode : CreateWalletUiState {
        override val isLoading: Boolean
            get() = false
    }
}