package com.akaam.app.duckwallet.ui.features.swap.selection


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.swap.SwapViewModel
import com.akaam.app.duckwallet.ui.theme.FullScreenTokenListDialog
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.TokenSelectingBox
import kotlin.reflect.KFunction1


@Composable
fun SwapSelectionRoute(
    modifier: Modifier = Modifier,
    viewModel: SwapViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,
) {
    val uiState by viewModel.selectionUiState.collectAsStateWithLifecycle()
    val tokenList by viewModel.tokenList.collectAsStateWithLifecycle()


    SwapSelectionScreen(
        uiState = uiState,
        modifier = modifier,
        originTokenInfo = viewModel.getOriginInfo(),
        destinationTokenInfo = viewModel.getDestinationInfo(),
        onNextStepClick = onNextStepClick,
        tokenList = tokenList,
        setOriginInfo = viewModel::setOriginInfo,
        setDistinctionInfo = viewModel ::setDestinationInfo
    )
}


@Composable
fun SwapSelectionScreen(
    uiState: SwapSelectionUiState,
    modifier: Modifier = Modifier,
    originTokenInfo: TokenInfo?,
    destinationTokenInfo: TokenInfo?,
    onNextStepClick: () -> Unit,
    tokenList: List<TokenInfo>,
    setDistinctionInfo: KFunction1<TokenInfo, Unit>,
    setOriginInfo: KFunction1<TokenInfo, Unit>,
) {

    var confirmDialogShowingState by remember {
        mutableStateOf(false)
    }
    var isOriginSelected by remember {
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
                if(isOriginSelected){
                    setOriginInfo.invoke(it)
                }
                else{
                    setDistinctionInfo.invoke(it)
                }
            },
           )
    }
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 30.dp),
        ) {
            val (originBox, destinationBox, transferIcon, tokenInfoTitle, tokenInfoBox, swapButton) = createRefs()

            TokenSelectingBox(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .constrainAs(originBox) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onClickAction = {
                    confirmDialogShowingState = true
                    isOriginSelected = true
                                },
                value = originTokenInfo?.name ?: "",
                label = stringResource(id = R.string.origin_title).uppercase(),
                leadingIcon = {
                    Icon(
                        painter = rememberAsyncImagePainter(originTokenInfo?.logoUrl),
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
                descriptionIcon = {
                    Column(modifier.padding(vertical = 5.dp, horizontal = 45.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.you_get,"").uppercase(),
                            color = MaterialTheme.colors.primary,
                            style =MaterialTheme.typography.caption
                        )
                        Text(
                            modifier = Modifier.alpha(0.3f),
                            text = stringResource(id = R.string.balance,"").uppercase(),
                            color = Color.Black,
                            style = MaterialTheme.typography.overline
                        )
                    }
                }
            )



            TokenSelectingBox(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .constrainAs(destinationBox) {
                        top.linkTo(originBox.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                    },
                onClickAction = { confirmDialogShowingState = true
                    isOriginSelected = false},
                value = destinationTokenInfo?.name ?: "",
                label = stringResource(id = R.string.destination_title).uppercase(),
                leadingIcon = {
                    Icon(
                        painter = rememberAsyncImagePainter(destinationTokenInfo?.logoUrl),
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

                descriptionIcon = {
                    Column(modifier.padding(vertical = 5.dp, horizontal = 35.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.you_pay,"").uppercase(),

                            color = MaterialTheme.colors.primary,
                            style =MaterialTheme.typography.caption
                        )
                        Text(
                            modifier = Modifier.alpha(0.3f),
                            text = stringResource(id = R.string.balance,"").uppercase(),
                            color = Color.Black,
                            style = MaterialTheme.typography.overline
                        )
                    }
                }
            )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                            .constrainAs(tokenInfoTitle) {

                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(tokenInfoBox.top)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.token_info_title).uppercase(),
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = getTokenNameForSwap(originTokenInfo, destinationTokenInfo),
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.caption
                        )

                    }

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .constrainAs(tokenInfoBox) {
                                top.linkTo(destinationBox.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(swapButton.top)

                            },

                        ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 35.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colors.secondaryVariant)
                                .padding(5.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            val textModifier =Modifier.padding(vertical = 5.dp, horizontal = 5.dp)

                            Row(
                                modifier = textModifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.swap_rate, ""),
                                    style = MaterialTheme.typography.caption
                                )
                                Text(
                                    text = getTokenNameForSwap(originTokenInfo, destinationTokenInfo, true),
                                    style = MaterialTheme.typography.caption
                                )
                            }
                            Text(
                                modifier = textModifier,
                                text = stringResource(id = R.string.swap_fee, ""),
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                modifier = textModifier,
                                text = stringResource(id = R.string.swap_price_impact, ""),
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                modifier = textModifier,
                                text = stringResource(id = R.string.swap_min_receive, ""),
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                    MainButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp, start = 30.dp, end = 30.dp)
                            .constrainAs(swapButton) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                height = Dimension.wrapContent
                            },
                        onClick = { onNextStepClick.invoke() },
                        text = stringResource(id = R.string.swap_button_title),
                        isTheMainBottomButton = true,
                        isSecondory = true
                    )

            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .offset(x = (-1).dp, y = (1).dp)
                    .constrainAs(transferIcon) {
                        top.linkTo(originBox.bottom)
                        start.linkTo(parent.start)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    },
                painter = painterResource(id = R.drawable.ic_swap),
                contentDescription = null
            )

        }





    when (uiState) {
        SwapSelectionUiState.Nothing -> {

        }

        SwapSelectionUiState.OnNextStepClicked -> {
            onNextStepClick()
        }
    }
}

fun getTokenNameForSwap(
    originTokenInfo: TokenInfo?,
    destinationTokenInfo: TokenInfo?,
    isGettingAmount: Boolean = false,
): String {
    return if (isGettingAmount) {
        "0.00 ${originTokenInfo?.name ?: "Token "} = 0.00 ${destinationTokenInfo?.name ?: "Token "} "
    } else {
        "${originTokenInfo?.name ?: "Token Name"} / ${destinationTokenInfo?.name ?: "Token Name"} "
    }
}


