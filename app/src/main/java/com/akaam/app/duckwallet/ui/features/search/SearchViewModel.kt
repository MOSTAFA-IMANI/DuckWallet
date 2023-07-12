package com.akaam.app.duckwallet.ui.features.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Nothing)
    val uiState = _uiState.asStateFlow()


    var searchedValue by mutableStateOf("")
        private set


    fun onSearchValueChanged(value: String){
        searchedValue = value
    }

}


