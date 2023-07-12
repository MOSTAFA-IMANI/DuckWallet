package com.akaam.app.duckwallet.ui.features.importwallet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.*

private const val TAG = "CreateWalletScreen"

@Composable
fun ImportWalletRoute(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ImportWalletViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val context =LocalContext.current

    CreateWalletScreen(
        uiState = state,
        walletName = viewModel.walletName,
        onWalletNameChanged = viewModel::updateWalletName,
        onNextStepClicked = viewModel::importWallet,
        modifier = modifier,
        onMnemonicInputChange = viewModel::updatePassword,
        mnemonicCodeList = viewModel.mnemonicCodeList
    )
//    HandleUiState(viewModel, navigateToYourMnemonicCode)
    when(state){
        ImportWalletUiState.Loading,ImportWalletUiState.Nothing -> Unit

        is ImportWalletUiState.EmptyInputFailure ->{
            Log.d(TAG, "CreateWalletScreen: EmptyInputFailure")
            ShowErrorMessage(stringResource(id = R.string.error_empty_inputs))
            viewModel.resetUiState()
        }
        ImportWalletUiState.NavigateToHome -> {
            navigateToHome()
        }
        is ImportWalletUiState.PassWordFailure -> {

        }
    }
}
@Composable
fun ShowToastMessageAndResetUiState(messageResourceId: Int, viewModel: ImportWalletViewModel) {
    ShowErrorMessage(stringResource(id = messageResourceId))
    viewModel.resetUiState()

}


@Composable
internal fun CreateWalletScreen(
    modifier: Modifier = Modifier,
    uiState: ImportWalletUiState,
    walletName: String,
    onWalletNameChanged: (walletName: String) -> Unit,
    onNextStepClicked: () -> Unit,
    onMnemonicInputChange: (String, Int) -> Unit,
    mnemonicCodeList: List<String>,

    ) {

    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_import_wallet).uppercase(),
        isScrollable = false,
        actionContent = {
            MainButton(onClick = { onNextStepClicked.invoke() },
                text = stringResource(id = R.string.import_button),
                isTheMainBottomButton = true,
                isSecondory =true )
        }
    ){
        ImportWalletInputs(
            uiState = uiState,
            walletName = walletName,
            onWalletNameChanged = onWalletNameChanged,
            onMnemonicInputChange =onMnemonicInputChange,
            mnemonicCodeList =mnemonicCodeList
        )

    }
}

@Composable
fun ImportWalletInputs(
    uiState: ImportWalletUiState,
    walletName: String,
    onWalletNameChanged: (username: String) -> Unit,
    onMnemonicInputChange: (String, Int) -> Unit,
    mnemonicCodeList: List<String>,

    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Spacer(modifier = Modifier.height(24.dp))
        MainEditText(
            value = walletName,
            onValueChange = { value -> onWalletNameChanged(value) },
            label = stringResource(id = R.string.wallet_name)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        MnemonicCode(codeList = mnemonicCodeList, onValueChange = {text, index -> onMnemonicInputChange(text,index) } )
        Spacer(modifier = Modifier.padding(5.dp))
    }

}
