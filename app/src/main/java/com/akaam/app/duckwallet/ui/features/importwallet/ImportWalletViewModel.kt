package com.akaam.app.duckwallet.ui.features.importwallet

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.ui.util.Constants
import com.akaam.app.duckwallet.ui.util.Constants.emptyListForCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class ImportWalletViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<ImportWalletUiState>(ImportWalletUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    var walletName by mutableStateOf("")
        private set

//    val mnemonicCodeList :MutableStateFlow<MutableList<String>> = MutableStateFlow(emptyListForCode().toMutableList())
    private val _itemList = mutableStateListOf<String>()
    val mnemonicCodeList: List<String> = _itemList

    init {
            _itemList.addAll(emptyListForCode())
    }


    fun updateWalletName(input: String) {
        walletName = input
    }
    fun updatePassword(content: String,index:Int) {
        _itemList[index] = content

        Log.d("TAG", "updatePassword: ${mnemonicCodeList[index]} c:$content ")
    }



    fun importWallet() {
        if (walletName.isBlank()|| _itemList.hasEmptyItem() ) {
            _uiState.value = ImportWalletUiState.EmptyInputFailure()
            return
        }


        // TODO: Call for Api
        _uiState.value =  (ImportWalletUiState.NavigateToHome)

    }


    fun resetUiState() {
        _uiState.value = ImportWalletUiState.Nothing
    }


}

private fun <String> List<String>.hasEmptyItem(): Boolean {
    this.forEach {
        it as kotlin.String
        if (it.isBlank())
            return true
    }
    return false
}
