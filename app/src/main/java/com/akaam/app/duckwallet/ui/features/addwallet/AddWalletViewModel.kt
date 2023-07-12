package com.akaam.app.duckwallet.ui.features.addwallet

import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.AddressBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddWalletViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow<AddWalletUiState>(AddWalletUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _walletList = MutableStateFlow<List<AddressBook>>(listOf())
    val walletList = _walletList.asStateFlow()

    init {
        _walletList.value = listOf(
            AddressBook("wallet Name 1", "walletAddressGettingFromServer1"),
            AddressBook("wallet Name 1", "walletAddressServer2"),
            AddressBook("wallet Name 1", "walletAddressFromServer3")
        )
    }

}


