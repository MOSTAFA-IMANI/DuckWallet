package com.akaam.app.duckwallet.ui.features.mnemonic

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import androidx.work.ListenableWorker.Result.Success

@Immutable
sealed interface MnemonicCodeUiState {
    val isLoading: Boolean

    object Nothing : MnemonicCodeUiState{
        override val isLoading: Boolean
            get() = false
    }

    object Loading : MnemonicCodeUiState{
        override val isLoading: Boolean
            get() = true
    }

    object NavigateToVerifyMnemonic : MnemonicCodeUiState{
        override val isLoading: Boolean
            get() = false
    }

    data class QrCodeHasGenerated(val bitmap:Bitmap? = null,val isSuccess: Boolean=true) : MnemonicCodeUiState{
        override val isLoading: Boolean
            get() = false
    }
}