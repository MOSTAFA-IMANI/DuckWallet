package com.akaam.app.duckwallet.ui.features.stake.selection


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.stake.StakeViewModel
import com.akaam.app.duckwallet.ui.theme.ActionScaffold
import com.akaam.app.duckwallet.ui.theme.FullScreenTokenListDialog
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.MainEditText
import com.akaam.app.duckwallet.ui.theme.TokenSelectingBox
import kotlin.reflect.KFunction1


@Composable
fun StakeSelectionRoute(
    modifier: Modifier = Modifier,
    viewModel: StakeViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,

    ) {
    val uiState by viewModel.uiStateSelection.collectAsStateWithLifecycle()
    val tokenList by viewModel.tokenList.collectAsStateWithLifecycle()


    StakeSelectionScreen(
        uiState = uiState,
        modifier = modifier,
        onNextStepClick = onNextStepClick,
        sendingTokenInfo = viewModel.getSendingTokenInfo(),
        amount = viewModel.sendingTokenAmount,
        onAmountUpdate = viewModel::updateAmount,
        amountInDollar = viewModel.sendingTokenAmountUSD,
        onEnergyClicked = {},
        onBandwidthClicked = {},
        tokenList = tokenList,
        setSendingTokenInfo = viewModel::setSendingTokenInfo,


        )
}


@Composable
fun StakeSelectionScreen(
    uiState: StakeSelectionUiState,
    modifier: Modifier = Modifier,
    onNextStepClick: () -> Unit,
    sendingTokenInfo: TokenInfo?,
    amount: Double,
    onAmountUpdate: KFunction1<String, Unit>,
    amountInDollar: String,
    onBandwidthClicked: () -> Unit,
    onEnergyClicked: () -> Unit,
    tokenList: List<TokenInfo>,
    setSendingTokenInfo: KFunction1<TokenInfo, Unit>,
) {


    ActionScaffold(appBarTitle = stringResource(id = R.string.screen_title_stake).uppercase(),
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

        MainStakeContent(

            sendingTokenInfo,
            amount,
            onAmountUpdate,
            amountInDollar,
            onEnergyClicked,
            onBandwidthClicked,
            tokenList,
            setSendingTokenInfo
        )
    }


    when (uiState) {
        StakeSelectionUiState.Nothing -> {}
    }
}

@Composable
private fun MainStakeContent(

    sendingTokenInfo: TokenInfo?,
    amount: Double,
    onAmountUpdate: KFunction1<String, Unit>,
    amountInDollar: String,
    onEnergyClicked: () -> Unit,
    onBandwidthClicked: () -> Unit,
    tokenList: List<TokenInfo>,
    setSendingTokenInfo: (TokenInfo) -> Unit,
) {
    var confirmDialogShowingState by remember { mutableStateOf(false) }

    if (confirmDialogShowingState) {
        val context = LocalContext.current
        FullScreenTokenListDialog(
            tokenList = tokenList,
            setShowDialog = {
                confirmDialogShowingState = it
            },
            onConfirm = {
                setSendingTokenInfo.invoke(it)
            },
        )
    }
    Column {

        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .clip(RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp)
        ) {

            TokenSelectingBox(
                onClickAction = { confirmDialogShowingState = true },
                value = sendingTokenInfo?.name ?: "",
                hint = stringResource(id = R.string.select_token),
                label = stringResource(id = R.string.token_info_title).uppercase(),
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
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val buttonModifier = Modifier
                .wrapContentWidth()
                .weight(0.5f)
            val buttonTextSize = MaterialTheme.typography.overline

            MainButton(
                modifier = buttonModifier,
                onClick = { onEnergyClicked.invoke() },
                text = stringResource(id = R.string.energy),
                textSize = buttonTextSize.fontSize,
                isTheMainBottomButton = false,
                isSecondory = true
            )
            MainButton(
                modifier = buttonModifier,
                onClick = { onBandwidthClicked.invoke() },
                textSize = buttonTextSize.fontSize,
                text = stringResource(id = R.string.bandwith, ""),
                isTheMainBottomButton = false,
                isSecondory = false
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.estimated),
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = stringResource(id = R.string.stake_to),
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = stringResource(id = R.string.stake_get),
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.primary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val boxModifier1 = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.primary)
                .wrapContentWidth()
                .weight(0.5f)
            val boxModifier2 = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.secondary)
                .wrapContentWidth()
                .weight(0.5f)

            EstimatedBox(boxModifier1)
            ArrowIcon()
            GetBox(boxModifier2)
        }
    }
}

@Composable
fun ArrowIcon() {
    Image(
        modifier = Modifier.padding(5.dp),
        painter = painterResource(id = R.drawable.ic_arow),
        contentDescription = null
    )
}

@Composable
fun GetBox(boxModifier: Modifier) {
    Box(
        modifier = boxModifier,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            textAlign = TextAlign.Start,
            text = "0.00",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground

        )

        Spacer(modifier = Modifier.height(92.dp))
    }
}

@Composable
fun EstimatedBox(boxModifier: Modifier) {
    Box(
        modifier = boxModifier,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            textAlign = TextAlign.Start,
            text = "0.00",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(92.dp))
    }
}




