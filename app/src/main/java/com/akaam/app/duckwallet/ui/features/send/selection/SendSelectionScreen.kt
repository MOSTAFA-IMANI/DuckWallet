package com.akaam.app.duckwallet.ui.features.send.selection


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.send.SendViewModel
import com.akaam.app.duckwallet.ui.theme.FullScreenTokenListDialog
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.MainEditText
import com.akaam.app.duckwallet.ui.theme.TokenSelectingBox
import kotlin.reflect.KFunction1


@Composable
fun SendSelectionRoute(
    modifier: Modifier = Modifier,
    viewModel: SendViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,

    ) {
    val uiState by viewModel.uiStateSelection.collectAsStateWithLifecycle()
    val tokenList by viewModel.tokenList.collectAsStateWithLifecycle()


    SendSelectionScreen(
        uiState = uiState,
        modifier = modifier,
        onNextStepClick = onNextStepClick,
        sendingTokenInfo = viewModel.getSendingTokenInfo(),
        amount = viewModel.sendingTokenAmount,
        onAmountUpdate = viewModel::updateAmount,
        amountInDollar = viewModel.sendingTokenAmountUSD,
        transferNote = viewModel.transferNote,
        onTransferNoteUpdated = viewModel::updateTransferNote,
                tokenList = tokenList,
        setSendingInfo = viewModel::setSendingTokenInfo,


        )
}


@Composable
fun SendSelectionScreen(
    uiState: SendSelectionUiState,
    modifier: Modifier = Modifier,
    onNextStepClick: () -> Unit,
    sendingTokenInfo: TokenInfo?,
    amount: Double,
    onAmountUpdate: KFunction1<String, Unit>,
    amountInDollar: String,
    onTransferNoteUpdated: KFunction1<String, Unit>,
    transferNote: String,
    tokenList: List<TokenInfo>,
    setSendingInfo: KFunction1<TokenInfo, Unit>,
) {
    var confirmDialogShowingState by remember {
        mutableStateOf(false)
    }

    if (confirmDialogShowingState) {
        val context = LocalContext.current
        FullScreenTokenListDialog(
            tokenList = tokenList,
            setShowDialog = {
                confirmDialogShowingState = it
            },
            onConfirm = {
                setSendingInfo.invoke(it)

            },
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        TokenSelectingBox(
            onClickAction = {confirmDialogShowingState =true},
            value = sendingTokenInfo?.name ?: "",
            hint = stringResource(id = R.string.select_token),
            label = stringResource(id = R.string.buy_title).uppercase(),
            leadingIcon = {
                Icon(
                    painter = rememberAsyncImagePainter(sendingTokenInfo?.logoUrl),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .width(20.dp)
                        .rotate(180f),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                )
            },
        )


        MainEditText(
            label = stringResource(id = R.string.token_amount).uppercase(),
            value = amount.toString(),
            onValueChange = onAmountUpdate,
            descriptionIcon = {
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                        .alpha(0.5f),
                    text = stringResource(
                        id = R.string.availible_amount_to_send,
                        sendingTokenInfo?.amount ?: "0.0"
                    ),
                )
            },
            trailingIcon = {
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                        .alpha(0.5f),
                    text = amountInDollar,
                )
            })



        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = stringResource(id = R.string.transfer_note_title),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption
        )
        BasicTextField(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            value = transferNote,
            onValueChange = onTransferNoteUpdated,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    if (transferNote.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.transfer_note_description),
                            style = MaterialTheme.typography.caption,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }
            }
        )

        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 30.dp, end = 30.dp),
            onClick = { onNextStepClick.invoke() },
            text = stringResource(id = R.string.next_step),
            isTheMainBottomButton = true,
            isSecondory = true
        )

    }





    when (uiState) {
        SendSelectionUiState.Nothing -> {}

    }
}




