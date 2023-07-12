package com.akaam.app.duckwallet.ui.features.watchwallet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.*
import com.akaam.app.duckwallet.ui.theme.Typography

private const val TAG = "CreateWalletScreen"

@Composable
fun WatchWalletRoute(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WatchWalletViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context =LocalContext.current

    WatchWalletScreen(
        uiState = state,
        walletName = viewModel.walletName,
        walletAddress = viewModel.walletAddress,
        onWalletNameChanged = viewModel::updateUsername,
        onWalletAddressChanged = viewModel::updateWalletAddress,
        onNextStepClicked = viewModel::createWallet,
        modifier = modifier
    )
//    HandleUiState(viewModel, navigateToYourMnemonicCode)
    when(state){
        WatchWalletUiState.Loading,WatchWalletUiState.Nothing -> Unit
        WatchWalletUiState.NavigateToHome -> {
            navigateToHome()
        }
        is WatchWalletUiState.PassWordFailure -> {
            Log.d(TAG, "CreateWalletScreen: PassWordVerifyFailure")
            ShowToastMessageAndResetUiState(R.string.error_password_confirm,viewModel)

        }

        is WatchWalletUiState.EmptyInputFailure ->{
            Log.d(TAG, "CreateWalletScreen: EmptyInputFailure")
            ShowToastMessageAndResetUiState(R.string.error_empty_inputs,viewModel)
        }

    }
}
@Composable
fun ShowToastMessageAndResetUiState(messageResourceId: Int, viewModel: WatchWalletViewModel) {
    ShowErrorMessage(stringResource(id = messageResourceId))
    viewModel.resetUiState()

}

@Composable
internal fun WatchWalletScreen(

    modifier: Modifier = Modifier,
    uiState: WatchWalletUiState,
    walletName: String,
    walletAddress: String,
    onWalletNameChanged: (walletName: String) -> Unit,
    onWalletAddressChanged: (password: String) -> Unit,
    onNextStepClicked: () -> Unit,

    ) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeScaffold(
            appBarTitle = stringResource(id = R.string.screen_title_watch_wallet).uppercase(),
            isScrollable = true,
            actionContent = {
                MainButton(onClick = { onNextStepClicked() },
                    text = stringResource(id = R.string.done_button_title),
                    isTheMainBottomButton = true,
                    isSecondory =true )
            }
        ){
            CreateWalletInputs(
                uiState = uiState,
                walletName = walletName,
                password = walletAddress,
                onUsernameChanged = onWalletNameChanged,
                onPasswordChanged = onWalletAddressChanged,

                )

        }
    }


}

@Composable
fun CreateWalletInputs(
    uiState: WatchWalletUiState,
    walletName: String,
    password: String,
    onUsernameChanged: (username: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,

    ) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(24.dp))
        Text(style = Typography.body2,
            text = stringResource(id = R.string.watch_wallet_title),
            color = MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(style = Typography.caption,
            text = stringResource(id = R.string.watch_wallet_description),
            color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.padding(5.dp))

        MainEditText(
            value = walletName,
            onValueChange = { value -> onUsernameChanged(value) },
            label = stringResource(id = R.string.wallet_name),
            hint =  stringResource(id = R.string.watch_only)
        )

        Spacer(modifier = Modifier.height(12.dp))
        MainEditText(
            value = password,
            onValueChange = { value -> onPasswordChanged(value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            label = stringResource(id = R.string.account),
            hint =  stringResource(id = R.string.enter_watch_only_wallet_address)

            )
        Spacer(modifier = Modifier.height(12.dp))


        Spacer(modifier = Modifier.padding(2.dp))

        Spacer(modifier = Modifier.padding(5.dp))
    }
}
