package com.akaam.app.duckwallet.ui.features.pairledger

import androidx.compose.runtime.Immutable

@Immutable
sealed interface PairLedgerUiState {
    val isLoading: Boolean

    object Nothing : PairLedgerUiState {
        override val isLoading: Boolean
            get() = false
    }

    object Loading : PairLedgerUiState {
        override val isLoading: Boolean
            get() = true
    }

    object NavigateToAddNewDevice : PairLedgerUiState {
        override val isLoading: Boolean
            get() = false
    }


}