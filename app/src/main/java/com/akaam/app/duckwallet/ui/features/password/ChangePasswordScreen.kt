package com.akaam.app.duckwallet.ui.features.password


import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ClickableText
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.PlaneEditText
import com.akaam.app.duckwallet.ui.theme.ProfileScaffold


@Composable
fun ChangePasswordRoute(
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChangePasswordScreen(
        uiState = uiState,
        modifier = modifier,
        oldPassValue = viewModel.oldPassValue,
        newPassValue = viewModel.newPassValue,
        reNewPassValue = viewModel.reNewPassValue,
        onOldPassValueChanged = viewModel::onOldPassValueChanged,
        onNewPassValueChanged = viewModel::onNewPassValueChanged,
        onReNewPassValueChanged = viewModel::onReNewPassValueChanged
    )
}


@Composable
private fun ChangePasswordScreen(
    uiState: ChangePasswordUiState,
    modifier: Modifier = Modifier,
    oldPassValue: String,
    reNewPassValue: String,
    newPassValue: String,
    onOldPassValueChanged: (String) -> Unit,
    onNewPassValueChanged: (String) -> Unit,
    onReNewPassValueChanged: (String) -> Unit,
) {
    val localBackHandler: OnBackPressedDispatcherOwner? = LocalOnBackPressedDispatcherOwner.current
    ProfileScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_chane_password).uppercase(),
        actionContent = {
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 30.dp, end = 30.dp),
                onClick = {
                    localBackHandler?.onBackPressedDispatcher?.onBackPressed()
                },
                text = stringResource(id = R.string.done_button_title),
                isTheMainBottomButton = true,
                isSecondory = true
            )
        }
    )
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
            HeaderSelection()
            Divider(
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                color = MaterialTheme.colors.primary,
                thickness = 1.dp
            )
            OldPassword(oldPassValue, onOldPassValueChanged)
            NewPassword(newPassValue, onNewPassValueChanged)
            ReNewPassword(reNewPassValue, onReNewPassValueChanged)


        }
    }

    when (uiState) {
        ChangePasswordUiState.Nothing -> {

        }
    }
}

@Composable
fun OldPassword(oldPassValue: String, onOldPassValueChanged: (String) -> Unit) {
    Text(
        text = stringResource(id = R.string.old_password),
        style = MaterialTheme.typography.body2
    )
    PlaneEditText(
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
        value = oldPassValue,
        onValueChange = onOldPassValueChanged,
        hint = stringResource(id = R.string.wallet_name),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        })

}

@Composable
fun NewPassword(newPassValue: String, onNewPassValueChanged: (String) -> Unit) {

    Text(
        text = stringResource(id = R.string.old_password),
        style = MaterialTheme.typography.body2
    )
    PlaneEditText(
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
        value = newPassValue,
        onValueChange = onNewPassValueChanged,
        hint = stringResource(id = R.string.wallet_name),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        })

}

@Composable
fun ReNewPassword(reNewPassValue: String, onReNewPassValueChanged: (String) -> Unit) {
    Text(
        text = stringResource(id = R.string.old_password),
        style = MaterialTheme.typography.body2
    )
    PlaneEditText(
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
        value = reNewPassValue,
        onValueChange = onReNewPassValueChanged,
        hint = stringResource(id = R.string.wallet_name),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        })

}

@Composable
fun HeaderSelection() {

    var isLocalPasswordSelected: Boolean by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ClickableText(
            text = stringResource(id = R.string.transaction_password),
            style = MaterialTheme.typography.caption,
            color = if (!isLocalPasswordSelected) MaterialTheme.colors.primary
            else MaterialTheme.colors.secondary,
            onClick = {
                isLocalPasswordSelected = false
            })
        ClickableText(text = stringResource(id = R.string.lock_screen_password),
            style = MaterialTheme.typography.caption,
            color = if (isLocalPasswordSelected) MaterialTheme.colors.primary
            else MaterialTheme.colors.secondary,
            onClick = {
                isLocalPasswordSelected = true

            })


    }
}




