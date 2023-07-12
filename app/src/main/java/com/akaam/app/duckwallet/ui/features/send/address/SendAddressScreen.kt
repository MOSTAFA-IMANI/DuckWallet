package com.akaam.app.duckwallet.ui.features.send.address


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.send.SendViewModel
import com.akaam.app.duckwallet.ui.theme.ActionScaffold
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.MainEditText


@Composable
fun SendAddressRoute(
    modifier: Modifier = Modifier,
    viewModel: SendViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,
    onAddressBookClick: () -> Unit,
    onMyAccountsClick: () -> Unit,
    onRecentClick: () -> Unit,
) {
    val uiState by viewModel.uiStateAddress.collectAsStateWithLifecycle()


    SendAddressScreen(
        uiState = uiState,
        modifier = modifier,
        onUpdateReceivingAccountAddress= viewModel::updateReceivingAccountAddress,
        receivingAccountAddress =viewModel.receivingAccountAddress,
        onNextStepClick = onNextStepClick,
        onAddressBookClick = onAddressBookClick,
        onMyAccountsClick = onMyAccountsClick,
        onRecentClick = onRecentClick,

    )
}


@Composable
fun SendAddressScreen(
    uiState: SendAddressUiState,
    modifier: Modifier = Modifier,
    onNextStepClick: () -> Unit,
    onUpdateReceivingAccountAddress:(String)-> Unit,
    receivingAccountAddress: String,
    onAddressBookClick: () -> Unit,
    onMyAccountsClick: () -> Unit,
    onRecentClick: () -> Unit,
) {


    ActionScaffold(appBarTitle = stringResource(id = R.string.screen_title_send).uppercase(),
    actionContent = {
        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 30.dp, end = 30.dp),
            onClick = { onNextStepClick.invoke() },
            text = stringResource(id = R.string.next_step),
            isTheMainBottomButton = true,
            isSecondory = true
        )

    }) {

        MainContent(
            receivingAccountAddress,
            onUpdateReceivingAccountAddress,
            onAddressBookClick,
            onMyAccountsClick,
            onRecentClick
        )
    }

    when (uiState) {
        SendAddressUiState.Nothing -> {}

    }
}

@Composable
private fun MainContent(
    receivingAccountAddress: String,
    onUpdateReceivingAccountAddress: (String) -> Unit,
    onAddressBookClick: () -> Unit,
    onMyAccountsClick: () -> Unit,
    onRecentClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        MainEditText(
            label = stringResource(id = R.string.address).uppercase(),
            value = receivingAccountAddress,
            onValueChange = onUpdateReceivingAccountAddress,
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
            })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val buttonModifier = Modifier.wrapContentWidth()
            val buttonTextSize = MaterialTheme.typography.overline
            MainButton(
                modifier = buttonModifier,
                onClick = { onAddressBookClick.invoke() },
                textSize = buttonTextSize.fontSize,
                text = stringResource(id = R.string.title_address_book_route).uppercase(),
                isTheMainBottomButton = false,
                isSecondory = false
            )
            MainButton(
                modifier = buttonModifier,
                onClick = { onMyAccountsClick.invoke() },
                text = stringResource(id = R.string.my_account).uppercase(),
                textSize = buttonTextSize.fontSize,
                isTheMainBottomButton = false,
                isSecondory = false
            )
            MainButton(
                modifier = buttonModifier,
                onClick = { onRecentClick.invoke() },
                textSize = buttonTextSize.fontSize,
                text = stringResource(id = R.string.recent).uppercase(),
                isTheMainBottomButton = false,
                isSecondory = false
            )
        }
    }
}




