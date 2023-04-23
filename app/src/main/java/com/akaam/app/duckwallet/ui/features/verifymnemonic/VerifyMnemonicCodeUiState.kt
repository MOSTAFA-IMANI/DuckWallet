package com.akaam.app.duckwallet.ui.features.verifymnemonic

import androidx.compose.runtime.Immutable

@Immutable
sealed interface VerifyMnemonicCodeUiState {
    val isLoading: Boolean

    object Nothing : VerifyMnemonicCodeUiState{
        override val isLoading: Boolean
            get() = false
    }

    object Loading : VerifyMnemonicCodeUiState{
        override val isLoading: Boolean
            get() = true
    }

    object NavigateToHome : VerifyMnemonicCodeUiState{
        override val isLoading: Boolean
            get() = false
    }

   object EmptyInputException: VerifyMnemonicCodeUiState{
       override val isLoading: Boolean
           get() = false
   }
     object IncorrectInputException:VerifyMnemonicCodeUiState{
         override val isLoading: Boolean
             get() = false
     }

}