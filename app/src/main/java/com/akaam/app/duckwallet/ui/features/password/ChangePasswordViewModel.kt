package com.akaam.app.duckwallet.ui.features.password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<ChangePasswordUiState>(ChangePasswordUiState.Nothing)
    val uiState = _uiState.asStateFlow()


    var oldPassValue by mutableStateOf("")
        private set
    var newPassValue by mutableStateOf("")
        private set
    var reNewPassValue by mutableStateOf("")
        private set


    fun onOldPassValueChanged(value: String) {
        oldPassValue = value
    }

    fun onNewPassValueChanged(value: String) {
        newPassValue = value
    }

    fun onReNewPassValueChanged(value: String) {
        reNewPassValue = value
    }

}


