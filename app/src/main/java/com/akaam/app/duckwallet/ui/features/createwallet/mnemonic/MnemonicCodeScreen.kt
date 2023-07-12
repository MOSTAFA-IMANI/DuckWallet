package com.akaam.app.duckwallet.ui.features.createwallet.mnemonic

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.BottomSpacer
import com.akaam.app.duckwallet.ui.theme.FlatButton
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.MnemonicCode
import com.akaam.app.duckwallet.ui.theme.Typography
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold
import kotlin.reflect.KFunction0

private val TAG = "MnemonicCodeScreen"
@Composable
fun MnemonicCodeRoute(
    navigateToVerifyMnemonicCode: () -> Unit,
    viewModel: MnemonicViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle(MnemonicCodeUiState.Loading)
    val codeState by viewModel.mnemonicCodeList.collectAsStateWithLifecycle()

    MnemonicCodeScreen(
        uiState = state,
        mnemonicCodeList = codeState,
        onCopyClick = viewModel::copyMnemonicListCode,
        onQRCodeClick = viewModel::generateQrCode,
        navigateToVerifyMnemonicCode = navigateToVerifyMnemonicCode,
        resetUiState = viewModel::resetUiState,
        modifier = modifier,
    )


}

@Composable
internal fun MnemonicCodeScreen(

    uiState: MnemonicCodeUiState,
    mnemonicCodeList: List<String>,
    onCopyClick: (Context) -> Unit,
    navigateToVerifyMnemonicCode: () -> Unit,
    onQRCodeClick: (Context) -> Unit,
    resetUiState: KFunction0<Unit>,
    modifier: Modifier,

    ) {
    var qrCodeDialogShowingState by remember {
        mutableStateOf(false)
    }
    Log.d(TAG, "MnemonicCodeScreen: $uiState")
    Log.d(TAG, "MnemonicCodeScreen:qrCodeDialogShowingState $qrCodeDialogShowingState")
    if (qrCodeDialogShowingState){

        if(uiState is MnemonicCodeUiState.QrCodeHasGenerated) {
            Dialog(onDismissRequest = {
                qrCodeDialogShowingState = false
                resetUiState()
            }) {
                uiState.bitmap?.asImageBitmap()?.let {
                    Log.d(TAG, "asImageBitmap: $it")
                    Image(bitmap = it, contentDescription = "")
                }

            }
        }
        else{
            resetUiState()
        }
    }
    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_mnemonic_code).uppercase(),
        actionContent = {
            MainButton(onClick = { navigateToVerifyMnemonicCode.invoke() },
                text = stringResource(id = R.string.continue_button_title),
                isTheMainBottomButton = true,
                isSecondory = true)
        }
    ){

            MnemonicCodeMainPart(
                mnemonicCodeList = mnemonicCodeList,
                onCopyClick = onCopyClick,
                onQRCodeClick = onQRCodeClick,
            )



    }


    when (uiState) {
        MnemonicCodeUiState.Nothing -> {
            qrCodeDialogShowingState = false
            Log.d(TAG, "uiState: Nothing")

        }
        MnemonicCodeUiState.Loading -> {
            Log.d(TAG, "uiState: Loading")
        }
        MnemonicCodeUiState.NavigateToVerifyMnemonic -> {
            Log.d(TAG, "uiState: NavigateToVerifyMnemonic")
            navigateToVerifyMnemonicCode()
        }
        is MnemonicCodeUiState.QrCodeHasGenerated -> {
            Log.d(TAG, "uiState: QrCodeHasGenerated")
            qrCodeDialogShowingState = true
        }

    }

}



@Composable
fun MnemonicCodeMainPart(
    mnemonicCodeList: List<String>,
    onCopyClick: (Context) -> Unit,
    onQRCodeClick: (Context) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))
        MnemonicCode(codeList = mnemonicCodeList)
        Spacer(modifier = Modifier.height(2.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            FlatButton(
                modifier = Modifier
                    .weight(0.25f)
                    .width(50.dp),
                onClick = {
                    onQRCodeClick.invoke(context)
                },
                text = stringResource(id = R.string.qr_code_button_title))
            Spacer(modifier = Modifier
                .padding(1.dp)
                .weight(0.25f))
            FlatButton(
                modifier = Modifier
                    .weight(0.25f)
                    .width(50.dp),
                onClick = {
                    onCopyClick.invoke(context)
                    Toast.makeText(context,context.getString(R.string.copy_button_click_message), Toast.LENGTH_SHORT).show()
                },
                text = stringResource(id = R.string.copy_button_title))

        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(style = Typography.caption,
            text = stringResource(id = R.string.mnemonic_code_tip),
            color = MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(style = Typography.body2,
            text = stringResource(id = R.string.mnemonic_code_warning_title),
            color = MaterialTheme.colors.error)
        Text(style = Typography.caption,
            text = stringResource(id = R.string.mnemonic_code_warning_description),
            color = MaterialTheme.colors.error)

        Spacer(modifier = Modifier.padding(5.dp))
    }
}
