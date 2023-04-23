package com.akaam.app.duckwallet.ui.features.watchwallet

import android.webkit.ConsoleMessage
import androidx.compose.runtime.Immutable
import com.akaam.app.duckwallet.domain.models.error.InputError

@Immutable
sealed interface WatchWalletUiState {
    val isLoading: Boolean

    object Nothing : WatchWalletUiState{
        override val isLoading: Boolean
            get() = false
    }  object Loading : WatchWalletUiState{
        override val isLoading: Boolean
            get() = true
    }
    data class PassWordFailure(val message: String?=null):WatchWalletUiState{
        override val isLoading: Boolean
            get() = false
    }


    data class EmptyInputFailure(val message: String?=null):WatchWalletUiState{
        override val isLoading: Boolean
            get() = false
    }
    object NavigateToHome : WatchWalletUiState{
        override val isLoading: Boolean
            get() = false
    }
}