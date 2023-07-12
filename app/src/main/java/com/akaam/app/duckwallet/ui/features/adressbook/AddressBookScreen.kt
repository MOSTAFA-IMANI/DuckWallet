

package com.akaam.app.duckwallet.ui.features.adressbook


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.AddressBook
import com.akaam.app.duckwallet.ui.theme.MainEditText
import com.akaam.app.duckwallet.ui.theme.ProfileScaffold
import kotlin.reflect.KFunction2


@Composable
fun AddressBookRoute(
    modifier: Modifier = Modifier,
    viewModel: AddressBookViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val addressBookList by viewModel.addressBookList.collectAsStateWithLifecycle()

    AddressBookScreen(
        uiState = uiState,
        addressBookList = addressBookList,
        onItemCopyClick = viewModel::copyAddressBook,
        modifier = modifier
    )
}


@Composable
fun AddressBookScreen(
    uiState: AddressBookUiState,
    modifier: Modifier = Modifier,
    addressBookList: List<AddressBook>,
    onItemCopyClick: KFunction2<Context, AddressBook, Unit>
) {
    val context = LocalContext.current
    val onCopyMessage = stringResource(id = R.string.copy_address_book)

    ProfileScaffold(appBarTitle = stringResource(id = R.string.screen_title_address_book).uppercase()) {

        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            items(addressBookList){addressBook->

                MainEditText(
                    value = addressBook.walletAddress,
                    onValueChange = {  },
                    label = addressBook.walletName,
                    enabled = false,
                    readOnly = false,
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = null,
                            modifier = Modifier
                                .width(12.dp)
                                .height(12.dp)
                                .clickable {
                                    onItemCopyClick.invoke(context, addressBook)

                                    Toast
                                        .makeText(
                                            context,
                                            onCopyMessage,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                },
                        )
                    }
                )
            }
        }
    }

    when (uiState) {
        AddressBookUiState.Nothing -> {}
    }
}


