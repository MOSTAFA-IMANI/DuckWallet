package com.akaam.app.duckwallet.ui.features.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Nothing)
    val uiState = _uiState.asStateFlow()


    fun onAllClicked(){
        _uiState.value = HistoryUiState.AllTabSelected
    }

    fun onSendClicked(){
        _uiState.value = HistoryUiState.SendTabSelected
    }

    fun onReceiveClicked(){

        _uiState.value = HistoryUiState.ReceiveTabSelected
    }

    fun onStakeClicked(){
        _uiState.value = HistoryUiState.StakeTabSelected
    }

}


