package com.akaam.app.duckwallet.ui.features.adddevice

import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.ui.features.mnemonic.MnemonicCodeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNewDeviceViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddNewDeviceUiState>(AddNewDeviceUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    private val tutorialPageCount:Int = 2

    private fun navigateToHome() {
        _uiState.value = AddNewDeviceUiState.NavigateToHome
    }
    fun onNextClicked(){
        _uiState.value = AddNewDeviceUiState.NotSupportError
    }
     fun resetUiState() {
        _uiState.value = AddNewDeviceUiState.Nothing
    }

}


