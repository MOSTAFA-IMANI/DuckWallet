package com.akaam.app.duckwallet.ui.features.home

import androidx.compose.runtime.Immutable

@Immutable
sealed interface HomeUiState {
    val isLoading: Boolean

    object Nothing : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }

    object Loading : HomeUiState {
        override val isLoading: Boolean
            get() = true
    }

    object WalletImported : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
    object WalletCreated : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NavToSend : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NavToReceive : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NavToSwap : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NavToStake : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
    object NavToBuy : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }

    object TopMenuIsShowing : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }

    data class Failure(val exception: Throwable) : HomeUiState {
        override val isLoading: Boolean
            get() = false
    }
}