package com.akaam.app.duckwallet.ui.features.sort

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SortUiState {


    object Nothing : SortUiState

    object SortByNameClicked : SortUiState
    object SortByVolumeClicked : SortUiState
    object SortByDateClicked : SortUiState


}