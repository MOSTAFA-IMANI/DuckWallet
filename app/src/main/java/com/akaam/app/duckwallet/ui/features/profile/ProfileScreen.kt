

package com.akaam.app.duckwallet.ui.features.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ClickableText
import com.akaam.app.duckwallet.ui.theme.MainButton


@Composable
fun ProfileRoute(
    navigateToWalletName : ()->Unit,
    navigateToChangeWallet : ()->Unit,
    navigateToChangePassword : ()->Unit,
    navigateToChangeAvatar : ()->Unit,
    navigateToLockedByDefault : ()->Unit,
    navigateToBackupPK : ()->Unit,
    navigateToBackupMnemonic : ()->Unit,
    navigateToDeleteWallet : ()->Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = uiState,
      navigateToWalletName= navigateToWalletName,
      navigateToChangeWallet= navigateToChangeWallet,
      navigateToChangePassword= navigateToChangePassword,
      navigateToChangeAvatar= navigateToChangeAvatar,
      navigateToLockedByDefault= navigateToLockedByDefault,
      navigateToBackupPK= navigateToBackupPK,
      navigateToBackupMnemonic= navigateToBackupMnemonic,
      navigateToDeleteWallet= navigateToDeleteWallet,
        modifier = modifier
    )
}


@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    navigateToWalletName : ()->Unit,
    navigateToChangeWallet : ()->Unit,
    navigateToChangePassword : ()->Unit,
    navigateToChangeAvatar : ()->Unit,
    navigateToLockedByDefault : ()->Unit,
    navigateToBackupPK : ()->Unit,
    navigateToBackupMnemonic : ()->Unit,
    navigateToDeleteWallet : ()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(text = stringResource(id = R.string.profile_wallet_name),onclick = navigateToWalletName)
        ClickableText(text = stringResource(id = R.string.profile_change_wallet), onclick = navigateToChangeWallet)
        ClickableText(text = stringResource(id = R.string.profile_change_password), onclick = navigateToChangePassword)
        ClickableText(text = stringResource(id = R.string.profile_change_avatar), onclick = navigateToChangeAvatar)
        ClickableText(text = stringResource(id = R.string.profile_lock_by_default), onclick = navigateToLockedByDefault)
        ClickableText(text = stringResource(id = R.string.profile_backup_pk), onclick = navigateToBackupPK)
        ClickableText(text = stringResource(id = R.string.profile_backup_mnemonic), onclick = navigateToBackupMnemonic)
        ClickableText(text = stringResource(id = R.string.profile_delete_wallet), onclick = navigateToDeleteWallet, color = MaterialTheme.colors.error)
               Spacer(Modifier.weight(1f))
    }

    when (uiState) {
        ProfileUiState.NavigateToBackupMnemonic ->{}
        ProfileUiState.NavigateToBackupPK ->{}
        ProfileUiState.NavigateToChangeAvatar ->{}
        ProfileUiState.NavigateToChangePassword ->{}
        ProfileUiState.NavigateToChangeWallet ->{}
        ProfileUiState.NavigateToDeleteWallet ->{}
        ProfileUiState.NavigateToLockedByDefault ->{}
        ProfileUiState.NavigateToWalletName ->{}
        ProfileUiState.Nothing ->{}
    }
}


