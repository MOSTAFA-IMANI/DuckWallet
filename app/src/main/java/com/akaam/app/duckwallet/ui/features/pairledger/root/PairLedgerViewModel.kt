package com.akaam.app.duckwallet.ui.features.pairledger.root

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PairLedgerViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<PairLedgerUiState>(PairLedgerUiState.Nothing)
    val uiState = _uiState.asStateFlow()
     var currentTutorialPage:MutableStateFlow<Int> = MutableStateFlow<Int>(1)
    private val tutorialPageCount:Int = 2

    private fun navigateToAddNewDevice() {
        _uiState.value = PairLedgerUiState.NavigateToAddNewDevice
    }
    fun onNextClicked(){
        if (currentTutorialPage.value<tutorialPageCount)
            currentTutorialPage.value++
        else{
            navigateToAddNewDevice()
        }
    }

}


