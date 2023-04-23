package com.akaam.app.duckwallet.ui.features.profile

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ProfileUiState {


    object Nothing : ProfileUiState

    object NavigateToWalletName : ProfileUiState
    object NavigateToChangeWallet : ProfileUiState
    object NavigateToChangePassword : ProfileUiState
    object NavigateToChangeAvatar : ProfileUiState
    object NavigateToLockedByDefault : ProfileUiState
    object NavigateToBackupPK: ProfileUiState
    object NavigateToBackupMnemonic: ProfileUiState
    object NavigateToDeleteWallet: ProfileUiState

}