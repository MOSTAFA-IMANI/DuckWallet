

package com.akaam.app.duckwallet.ui.features.profile


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.AddressBook
import com.akaam.app.duckwallet.ui.theme.ChooseTimeDialog
import com.akaam.app.duckwallet.ui.theme.ChooseWalletDialog
import com.akaam.app.duckwallet.ui.theme.ClickableText
import com.akaam.app.duckwallet.ui.theme.ProfileScaffold


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
    val walletList by viewModel.walletList.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = uiState,
        navigateToWalletName = navigateToWalletName,
        navigateToChangeWallet = navigateToChangeWallet,
        navigateToChangePassword = navigateToChangePassword,
        navigateToChangeAvatar = navigateToChangeAvatar,
        navigateToLockedByDefault = navigateToLockedByDefault,
        navigateToBackupPK = navigateToBackupPK,
        navigateToBackupMnemonic = navigateToBackupMnemonic,
        navigateToDeleteWallet = navigateToDeleteWallet,
        walletList = walletList,
        modifier = modifier
    )
}


@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    navigateToWalletName: () -> Unit,
    navigateToChangeWallet: () -> Unit,
    navigateToChangePassword: () -> Unit,
    navigateToChangeAvatar: () -> Unit,
    navigateToLockedByDefault: () -> Unit,
    navigateToBackupPK: () -> Unit,
    navigateToBackupMnemonic: () -> Unit,
    navigateToDeleteWallet: () -> Unit,
    modifier: Modifier = Modifier,
    walletList: List<AddressBook>,
) {
    var changeWalletDialogShowingState by remember {
        mutableStateOf(false)
    }
    var changeTimeDialogShowingState by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    if (changeWalletDialogShowingState) {
        ChooseWalletDialog(
            walletList = walletList,
            setShowDialog = {
                changeWalletDialogShowingState = it
            },
            onConfirm = {
                Toast.makeText(context, "Wallet Change, $it Selected ", Toast.LENGTH_SHORT)
                    .show()
            },
            onDismiss = {})
    }
    if (changeTimeDialogShowingState) {
        ChooseTimeDialog(
            setShowDialog = {
                changeTimeDialogShowingState = it
            },
            onConfirm = {
                Toast.makeText(context, "Default Time Changed: $it  ", Toast.LENGTH_SHORT)
                    .show()
            },
            onDismiss = {})

    }
    ProfileScaffold(appBarTitle = stringResource(id = R.string.screen_title_profile).uppercase()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = stringResource(id = R.string.profile_wallet_name),
                onClick = navigateToWalletName
            )
            ClickableText(
                text = stringResource(id = R.string.profile_change_wallet),
                onClick = { changeWalletDialogShowingState = true })
            ClickableText(
                text = stringResource(id = R.string.profile_change_password),
                onClick = navigateToChangePassword
            )
            ClickableText(
                text = stringResource(id = R.string.profile_change_avatar),
                onClick = navigateToChangeAvatar
            )
            ClickableText(text = stringResource(id = R.string.profile_lock_by_default),
                onClick = {
                    changeTimeDialogShowingState = true
                })
            ClickableText(
                text = stringResource(id = R.string.profile_backup_pk),
                onClick = navigateToBackupPK
            )
            ClickableText(
                text = stringResource(id = R.string.profile_backup_mnemonic),
                onClick = navigateToBackupMnemonic
            )
            ClickableText(
                text = stringResource(id = R.string.profile_delete_wallet),
                onClick = navigateToDeleteWallet,
                color = MaterialTheme.colors.error
            )
        }

    }

    when (uiState) {
        ProfileUiState.NavigateToBackupMnemonic -> {}
        ProfileUiState.NavigateToBackupPK -> {}
        ProfileUiState.NavigateToChangeAvatar -> {}
        ProfileUiState.NavigateToChangePassword -> {}
        ProfileUiState.NavigateToChangeWallet -> {}
        ProfileUiState.NavigateToDeleteWallet -> {}
        ProfileUiState.NavigateToLockedByDefault -> {}
        ProfileUiState.NavigateToWalletName -> {}
        ProfileUiState.Nothing -> {}
    }
}


