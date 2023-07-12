package com.akaam.app.duckwallet.ui.features.receive


import android.content.Context
import android.widget.Toast
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ActionScaffold
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.util.extension.copyTextToClipboard


@Composable
fun ReceiveRoute(
    modifier: Modifier = Modifier,
    viewModel: ReceiveViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val walletAddress by viewModel.walletAddress.collectAsStateWithLifecycle()

    val screenWidth = getScreenWidth()
    val qrCode = viewModel.getReceiveQrcode(screenWidth)
    val context = LocalContext.current

    ReceiveScreen(
        uiState = uiState,
        modifier = modifier,
        onNextStepClick = onNextStepClick,
        receiveQrCode = qrCode,
        walletAddress = walletAddress,
        onSaveQrClicked = viewModel::saveQr,
        onShareQrClicked = viewModel::shareQr
    )
}

@Composable
fun getScreenWidth(): Float {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenWidthPx = with(LocalDensity.current) {
        screenWidthDp.toPx()
    }
    return screenWidthPx
}


@Composable
fun ReceiveScreen(
    uiState: ReceiveUiState,
    modifier: Modifier = Modifier,
    onNextStepClick: () -> Unit,
    receiveQrCode: QRGEncoder,
    walletAddress: String,
    onSaveQrClicked:(QRGEncoder) ->Unit,
    onShareQrClicked:(Context, QRGEncoder)-> Unit,

    ) {

    ActionScaffold(appBarTitle = stringResource(id = R.string.screen_title_receive).uppercase(),
        actionContent = {
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 30.dp, end = 30.dp),
                onClick = { onNextStepClick.invoke() },
                text = stringResource(id = R.string.confirm_button_title),
                isTheMainBottomButton = true,
                isSecondory = true
            )
        }) {
        MainReceiveContent(receiveQrCode, walletAddress, onSaveQrClicked,onShareQrClicked)
    }

    when (uiState) {
        ReceiveUiState.Nothing -> {

        }

        ReceiveUiState.OnConfirmClicked -> {}
    }
}

@Composable
fun MainReceiveContent(
    receiveQrCode: QRGEncoder,
    walletAddress: String,
    onSaveQrClicked: (QRGEncoder) -> Unit,
    onShareQrClicked: (Context, QRGEncoder) -> Unit,
) {

    val context = LocalContext.current
    Column(
        Modifier
            .scrollable(rememberScrollState(), Orientation.Horizontal)
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.receive_title),
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
        receiveQrCode.bitmap?.asImageBitmap()?.let {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .background(Color.White)
                    .border(
                        width = 6.dp, color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(10.dp)

                    ),
                bitmap = it, contentDescription = stringResource(id = R.string.receive_title)
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 4.dp)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(5.dp),
            text = "Wallet Name",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 4.dp)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(5.dp)
                .clickable {
                    context.copyTextToClipboard("walletName", walletAddress)
                    Toast
                        .makeText(
                            context,
                            "WalletAddress has been copied into clipboard.",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
            text = walletAddress,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.save_qr).uppercase(),
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.clickable {
                    onSaveQrClicked.invoke(receiveQrCode)
                }
            )
            Text(
                text = stringResource(id = R.string.share_qr).uppercase(),
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.clickable {
                    onShareQrClicked.invoke(context, receiveQrCode)
                }
            )
        }
    }

}




