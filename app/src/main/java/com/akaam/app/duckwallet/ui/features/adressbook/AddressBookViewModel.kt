package com.akaam.app.duckwallet.ui.features.adressbook

import android.content.Context
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.AddressBook
import com.akaam.app.duckwallet.ui.util.extension.copyTextToClipboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddressBookViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddressBookUiState>(AddressBookUiState.Nothing)
    val uiState = _uiState.asStateFlow()


    private val _addressBookList = MutableStateFlow<List<AddressBook>>(listOf())
    val addressBookList = _addressBookList.asStateFlow()

    init{
        getAddressBookList()
    }

    private fun getAddressBookList() {
        val temporaryAddressBookList = ArrayList<AddressBook>()
        repeat(8){
            temporaryAddressBookList.add(AddressBook("WalletNameServer $it",getRandomString(10)))
        }
        _addressBookList.value = temporaryAddressBookList
    }
    private fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
    fun copyAddressBook(context: Context,walletAddressBook: AddressBook){
        context.copyTextToClipboard(walletAddressBook.walletName,walletAddressBook.walletAddress)
    }

}


