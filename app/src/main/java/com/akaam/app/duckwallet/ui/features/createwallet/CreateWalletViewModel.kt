package com.akaam.app.duckwallet.ui.features.createwallet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<CreateWalletUiState>(CreateWalletUiState.Nothing)
    val uiState = _uiState.asStateFlow()
    /*
    private val _uiState: MutableSharedFlow<CreateWalletUiState> =
        MutableSharedFlow()

    val uiState: SharedFlow<CreateWalletUiState> = _uiState
*/
    var walletName by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var passwordConfirm by mutableStateOf("")
        private set

    var isWeakPassword = mutableStateOf(true)

    fun updateUsername(input: String) {
        walletName = input
    }

    fun updatePassword(input: String) {
       isWeakPassword.value =  checkWeakPassword(input)
        password = input
    }

    fun updatePasswordConfirm(input: String) {
        passwordConfirm = input
    }

    fun createWallet(walletName: String, password: String, passwordVerify: String) {
        if (walletName.isBlank() || password.isBlank() || passwordVerify.isBlank()) {
            _uiState.value = CreateWalletUiState.EmptyInputFailure()
            return
        }
        if (password != passwordVerify) {
            _uiState.value = (CreateWalletUiState.PassWordVerifyFailure())
            return
        }

        if (checkWeakPassword(password)) {
            _uiState.value = (CreateWalletUiState.WeakPassWordFailure())
            return
        }
        // TODO: Call for Api
        _uiState.value =  (CreateWalletUiState.NavigateToYourMnemonicCode)
        /*viewModelScope.launch {

            if (walletName.isBlank() || password.isBlank() || passwordVerify.isBlank()) {
                _uiState.emit(CreateWalletUiState.EmptyInputFailure)
                return@launch
            }
            if (password != passwordVerify) {
                _uiState.emit(CreateWalletUiState.PassWordVerifyFailure)
                return@launch
            }

            if (checkWeakPassword(password)) {
                _uiState.emit(CreateWalletUiState.WeakPassWordFailure)
                return@launch
            }
            // TODO: Call for Api
            _uiState.emit(CreateWalletUiState.NavigateToYourMnemonicCode)
        }*/
    }

    private fun checkWeakPassword(password: String): Boolean {
        val isAtLeast8 = password.length >= 8 //Checks for at least 8 characters

        val hasLowercase = password != password.uppercase(Locale.getDefault())
        val hasUppercase = password != password.lowercase(Locale.getDefault())

        val pattern: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(password)
        val hasSpecialChar: Boolean = matcher.find()

        if(isAtLeast8 && hasLowercase && hasUppercase && hasSpecialChar){
            return false
        }
        return true
    }

    fun resetUiState() {
        _uiState.value = CreateWalletUiState.Nothing
    }


}
