package com.akaam.app.duckwallet.ui.features.watchwallet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class WatchWalletViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<WatchWalletUiState>(WatchWalletUiState.Nothing)
    val uiState = _uiState.asStateFlow()
    /*
    private val _uiState: MutableSharedFlow<WatchWalletUiState> =
        MutableSharedFlow()

    val uiState: SharedFlow<WatchWalletUiState> = _uiState
*/
    var walletName by mutableStateOf("test")
        private set
    var walletAddress by mutableStateOf("Mm123456@")
        private set



    fun updateUsername(input: String) {
        walletName = input
    }

    fun updateWalletAddress(input: String) {
        walletAddress = input
    }


    fun createWallet() {
        if (walletName.isBlank() || walletAddress.isBlank() ) {
            _uiState.value = WatchWalletUiState.EmptyInputFailure()
            return
        }



        // TODO: Call for Api
        _uiState.value =  (WatchWalletUiState.NavigateToHome)

    }


    fun resetUiState() {
        _uiState.value = WatchWalletUiState.Nothing
    }


}
