package com.akaam.app.duckwallet.ui.features.pairledger.adddevice


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.BottomSpacer
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.Typography
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold


@Composable
fun AddNewDeviceRoute(
    navigateToAddNewDevice: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddNewDeviceViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddNewDeviceScreen(
        uiState = uiState,
        navigateToHome = navigateToAddNewDevice,
        onNextButtonClicked = viewModel::onNextClicked,
       modifier = modifier,

    )
}


@Composable
fun AddNewDeviceScreen(
    uiState: AddNewDeviceUiState,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,

) {

    var errorDialogShowingState by remember {
        mutableStateOf(false)
    }
    if(errorDialogShowingState){
        AlertDialog(
            modifier= Modifier.padding(15.dp),
            onDismissRequest = {
                errorDialogShowingState=false
                               },
            text =  {
            Text(text = stringResource(id = R.string.error_not_support)) },
            title = {},
        confirmButton = {
            MainButton(onClick ={
                errorDialogShowingState=false
            } , text = stringResource(id = R.string.ok_button_title) )
        })
    }
    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_creat_wallet).uppercase(),
        actionContent = {

            MainButton(onClick = { errorDialogShowingState=true },
                text = stringResource(id = R.string.next_step),
                isTheMainBottomButton = true,
                isSecondory = true)
        }
    ){
        MainTutorialContent()

    }

    when (uiState) {
        AddNewDeviceUiState.Nothing, AddNewDeviceUiState.Loading -> {
        }
        AddNewDeviceUiState.NavigateToHome -> navigateToHome()
        AddNewDeviceUiState.NotSupportError -> {

        }
    }
}

@Composable
fun MainTutorialContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


            Spacer(modifier = Modifier.height(24.dp))
            Text(style = Typography.body2,
                text =  stringResource(id = R.string.search_for_ledger),
                color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.padding(5.dp))
        Text(style = Typography.caption,
            text = stringResource(id =R.string.add_new_divice_description_1),
            textAlign= TextAlign.Center,
            color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.padding(10.dp))
            Image(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),

                painter = painterResource(id = R.drawable.ic_pair_ledger_add_new_device), contentDescription = "")


        Spacer(modifier = Modifier.height(10.dp))
        Text(style = Typography.caption,
            text = stringResource(id =R.string.add_new_divice_description_2),
            textAlign= TextAlign.Center,
            color = MaterialTheme.colors.onSurface)
        Spacer(modifier = Modifier.height(10.dp))


    }
}


