
package com.akaam.app.duckwallet.ui.features.login

import com.akaam.app.duckwallet.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.ui.theme.icon.DuckWalletIcons


@Composable
fun LoginRoute(
    navigateToWelcome: () -> Unit,
    navigateToRegister: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = uiState,
        username = viewModel.username,
        password = viewModel.password,
        onUsernameChanged = viewModel::updateUsername,
        onPasswordChanged = viewModel::updatePassword,
        onLoginClicked = viewModel::login,
        onSignupNoticeClicked = navigateToRegister,
        navigateToMain = navigateToWelcome,
        onFailureOccurred = onFailureOccurred,
        modifier = modifier
    )
}


@Composable
fun LoginScreen(
    uiState: LoginUiState,
    username: String,
    password: String,
    onUsernameChanged: (username: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupNoticeClicked: () -> Unit,
    navigateToMain: () -> Unit,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoginInputs(
            uiState = uiState,
            username = username,
            password = password,
            onUsernameChanged = onUsernameChanged,
            onPasswordChanged = onPasswordChanged,
            onLoginClicked = onLoginClicked
        )
        SignUpNotice(onSignupClicked = onSignupNoticeClicked)
    }

    when (uiState) {
        LoginUiState.Nothing, LoginUiState.Loading -> Unit
        LoginUiState.SUCCESS -> {
            navigateToMain()
        }
        is LoginUiState.Failure -> {
            onFailureOccurred(uiState.exception)
        }
    }
}

@Composable
fun ColumnScope.LoginInputs(
    uiState: LoginUiState,
    username: String,
    password: String,
    onUsernameChanged: (username: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

        Text(
            text = stringResource(id =  R.string.login),
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { value -> onUsernameChanged(value) },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.wallet_name)) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { value -> onPasswordChanged(value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.password)) },
            trailingIcon = {
                val image =
                    if (isPasswordVisible) DuckWalletIcons.Visibility else DuckWalletIcons.VisibilityOff

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    image.ToIcon()
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    enabled = username.isNotBlank() && password.isNotBlank(),
                    onClick = { onLoginClicked() }
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            }
        }

    }
}


@Composable
fun SignUpNotice(
    onSignupClicked: () -> Unit
) {
    Row(
        modifier = Modifier.height(52.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.dont_have_account),

        )

        Text(
            modifier = Modifier
                .clickable { onSignupClicked() }
                .padding(8.dp),
            text = stringResource(id = R.string.register),

        )
    }
}

