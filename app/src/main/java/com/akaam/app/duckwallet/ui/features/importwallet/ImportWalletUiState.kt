package com.akaam.app.duckwallet.ui.features.importwallet

import android.webkit.ConsoleMessage
import androidx.compose.runtime.Immutable
import com.akaam.app.duckwallet.domain.models.error.InputError

@Immutable
sealed interface ImportWalletUiState {
    val isLoading: Boolean

    object Nothing : ImportWalletUiState{
        override val isLoading: Boolean
            get() = false
    }  object Loading : ImportWalletUiState{
        override val isLoading: Boolean
            get() = true
    }
    object PassWordFailure:ImportWalletUiState{
        override val isLoading: Boolean
            get() = false
    }


    data class EmptyInputFailure(val message: String?=null):ImportWalletUiState{
        override val isLoading: Boolean
            get() = false
    }
    object NavigateToHome : ImportWalletUiState{
        override val isLoading: Boolean
            get() = false
    }
}