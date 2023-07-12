package com.akaam.app.duckwallet.ui.features.createwallet.root

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.*
import com.akaam.app.duckwallet.ui.theme.Typography

private const val TAG = "CreateWalletScreen"

@Composable
fun CreateWalletRoute(
    navigateToYourMnemonicCode: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateWalletViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context =LocalContext.current

    CreateWalletScreen(
        uiState = state,
        walletName = viewModel.walletName,
        password = viewModel.password,
        passwordConfirm = viewModel.passwordConfirm,
        isWeakPassWord = viewModel.isWeakPassword,
        onWalletNameChanged = viewModel::updateUsername,
        onPasswordChanged = viewModel::updatePassword,
        onPasswordConfirmChanged = viewModel::updatePasswordConfirm,
        onNextStepClicked = viewModel::createWallet,
        modifier = modifier
    )
//    HandleUiState(viewModel, navigateToYourMnemonicCode)
    when(state){
        CreateWalletUiState.Loading, CreateWalletUiState.Nothing -> Unit
        CreateWalletUiState.NavigateToYourMnemonicCode -> {
            navigateToYourMnemonicCode()
        }
        is CreateWalletUiState.PassWordVerifyFailure -> {
            Log.d(TAG, "CreateWalletScreen: PassWordVerifyFailure")
            ShowToastMessageAndResetUiState(R.string.error_password_confirm,viewModel)

        }
        is CreateWalletUiState.WeakPassWordFailure -> {
            Log.d(TAG, "CreateWalletScreen: WeakPassWordFailure")
            ShowToastMessageAndResetUiState(R.string.error_password_confirm,viewModel)
            /*
            val snackbarService= SnackbarDelegate()
            snackbarService.showSnackbar(
                SnackbarState.ERROR,
                context.getString(R.string.error_password_confirm))
            */
            Toast.makeText(context, context.getString(R.string.error_weak_password), Toast.LENGTH_LONG).show()

//                    ShowErrorMessage(stringResource(id = R.string.error_weak_password))
        }
        is CreateWalletUiState.EmptyInputFailure ->{
            Log.d(TAG, "CreateWalletScreen: EmptyInputFailure")
            ShowToastMessageAndResetUiState(R.string.error_password_confirm,viewModel)
            Toast.makeText(context, context.getString(R.string.error_empty_inputs), Toast.LENGTH_LONG).show()

            ShowErrorMessage(stringResource(id = R.string.error_empty_inputs))
        }

    }
}
@Composable
fun ShowToastMessageAndResetUiState(messageResourceId: Int, viewModel: CreateWalletViewModel) {
    ShowErrorMessage(stringResource(id = messageResourceId))
    viewModel.resetUiState()

}

/*
@Composable
fun HandleUiState(viewModel: CreateWalletViewModel, navigateToYourMnemonicCode: () -> Unit) {
    val context =LocalContext.current
    LaunchedEffect(true) {
        viewModel.uiState.collect { uiState ->

            when(uiState){
                CreateWalletUiState.Loading -> {
                    Log.d(TAG, "CreateWalletScreen: Loading")
                }
                CreateWalletUiState.NavigateToYourMnemonicCode -> {
                    Log.d(TAG, "CreateWalletScreen: NavigateToYourMnemonicCode")
                    navigateToYourMnemonicCode()
                }
                CreateWalletUiState.PassWordVerifyFailure -> {
                    Log.d(TAG, "CreateWalletScreen: PassWordVerifyFailure")
                    val snackbarService= SnackbarDelegate()
                    snackbarService.showSnackbar(
                        SnackbarState.ERROR,
                        context.getString(R.string.error_password_confirm)
                    )
                    Toast.makeText(context, context.getString(R.string.error_password_confirm), Toast.LENGTH_LONG).show()
//                    ShowErrorMessage(stringResource(id = R.string.error_password_confirm))
                }
                CreateWalletUiState.EmptyInputFailure ->  {
                    Log.d(TAG, "CreateWalletScreen: EmptyInputFailure")
                    val snackbarService= SnackbarDelegate()
                    snackbarService.showSnackbar(
                        SnackbarState.ERROR,
                        context.getString(R.string.error_password_confirm))
                    Toast.makeText(context, context.getString(R.string.error_empty_inputs), Toast.LENGTH_LONG).show()

//                    ShowErrorMessage(stringResource(id = R.string.error_empty_inputs))
                }
                CreateWalletUiState.WeakPassWordFailure ->  {
                    Log.d(TAG, "CreateWalletScreen: WeakPassWordFailure")
                    val snackbarService= SnackbarDelegate()
                    snackbarService.showSnackbar(
                        SnackbarState.ERROR,
                        context.getString(R.string.error_password_confirm))
                    Toast.makeText(context, context.getString(R.string.error_weak_password), Toast.LENGTH_LONG).show()

//                    ShowErrorMessage(stringResource(id = R.string.error_weak_password))
                }
                CreateWalletUiState.Nothing -> {

                }
            }

        }
    }
}
*/

@Composable
internal fun CreateWalletScreen(

    modifier: Modifier = Modifier,
    uiState: CreateWalletUiState,
    walletName: String,
    password: String,
    passwordConfirm: String,
    onWalletNameChanged: (walletName: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onPasswordConfirmChanged: (passwordVerify: String) -> Unit,
    onNextStepClicked: (walletName: String, password: String, passwordVerify: String) -> Unit,
    isWeakPassWord: MutableState<Boolean>,

    ) {
    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_creat_wallet).uppercase(),
        isScrollable = true,
        actionContent = {
            MainButton(onClick = { onNextStepClicked.invoke(walletName,password,passwordConfirm) },
                text = stringResource(id = R.string.next_step),
                isTheMainBottomButton = true,
                isSecondory =true )
        }
        ){

            CreateWalletInputs(
                walletName = walletName,
                password = password,
                isWeakPassWord = isWeakPassWord,
                passwordConfirm = passwordConfirm,
                onUsernameChanged = onWalletNameChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordConfirmChanged = onPasswordConfirmChanged
            )


    }




}

@Composable
fun CreateWalletInputs(
    walletName: String,
    password: String,
    passwordConfirm: String,
    onUsernameChanged: (username: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onPasswordConfirmChanged: (passwordVerify: String) -> Unit,
    isWeakPassWord: MutableState<Boolean>,

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
            onValueChange = { value -> onUsernameChanged(value) },
            label = stringResource(id = R.string.wallet_name)
        )

        Spacer(modifier = Modifier.height(12.dp))
        MainEditText(
            value = password,
            onValueChange = { value -> onPasswordChanged(value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            label = stringResource(id = R.string.password),

            )
        Spacer(modifier = Modifier.height(12.dp))
        MainEditText(
            value = passwordConfirm,
            onValueChange = { value -> onPasswordConfirmChanged(value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            label = stringResource(id = R.string.confirm_password),
        )
        Text(
            modifier= Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = Typography.overline,
            text = if (isWeakPassWord.value)
                stringResource(id = R.string.weak_password)
            else
                stringResource(id = R.string.strong_password),
            color = if (isWeakPassWord.value)
                MaterialTheme.colors.error
            else
                MaterialTheme.colors.secondary,

        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(style = Typography.caption,
            text = stringResource(id = R.string.password_rule),
            color = MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(style = Typography.caption,
            text = stringResource(id = R.string.password_rule_notice),
            color = MaterialTheme.colors.secondary)

        Spacer(modifier = Modifier.padding(5.dp))
    }
}
