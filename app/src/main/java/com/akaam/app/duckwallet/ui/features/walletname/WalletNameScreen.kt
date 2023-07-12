package com.akaam.app.duckwallet.ui.features.walletname


import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.PlaneEditText
import com.akaam.app.duckwallet.ui.theme.ProfileScaffold


@Composable
fun WalletNameRoute(
    modifier: Modifier = Modifier,
    viewModel: WalletNameViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WalletNameScreen(
        uiState = uiState,
        modifier = modifier,
        searchedValue = viewModel.searchedValue,
        onSearchValueChanged = viewModel::onSearchValueChanged
    )
}


@Composable
fun WalletNameScreen(
    uiState: WalletNameUiState,
    modifier: Modifier = Modifier,
    searchedValue: String,
    onSearchValueChanged: (String) -> Unit,
) {
    val columnTitleModifier = Modifier.wrapContentWidth()
    val localBackHandler: OnBackPressedDispatcherOwner? = LocalOnBackPressedDispatcherOwner.current
    ProfileScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_wallet_name).uppercase(),
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

            ) {
            Text(
                text = stringResource(id = R.string.rename),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary
            )
            PlaneEditText(
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
                value = searchedValue,
                onValueChange = onSearchValueChanged,
                hint = stringResource(id = R.string.wallet_name)
            )

        }
    }

    when (uiState) {
        WalletNameUiState.Nothing -> {

        }
    }
}




