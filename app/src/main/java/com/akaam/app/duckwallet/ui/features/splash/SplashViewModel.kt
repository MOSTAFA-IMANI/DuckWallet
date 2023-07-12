package com.akaam.app.duckwallet.ui.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(

) : ViewModel() {
    //    val state: StateFlow<SplashUiState> = SplashUiState.Loading
    private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        viewModelScope.launch {
            delay(500)
            _uiState.value = SplashUiState.NavigateToHome

        }
    }
}
