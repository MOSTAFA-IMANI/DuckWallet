package com.akaam.app.duckwallet.ui.features.sort

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<SortUiState>(SortUiState.Nothing)
    val uiState = _uiState.asStateFlow()


}


