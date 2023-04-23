package com.akaam.app.duckwallet.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun login() {
        _uiState.value = LoginUiState.SUCCESS
    }
}


