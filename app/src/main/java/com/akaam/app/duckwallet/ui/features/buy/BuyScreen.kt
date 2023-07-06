package com.akaam.app.duckwallet.ui.features.buy


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.ThirdPartyProvider
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.theme.FullScreenTokenListDialog
import com.akaam.app.duckwallet.ui.theme.MainEditText
import com.akaam.app.duckwallet.ui.theme.TokenSelectingBox
import kotlin.reflect.KFunction1


@Composable
fun BuyRoute(
    modifier: Modifier = Modifier,
    viewModel: BuyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tokenList by viewModel.tokenList.collectAsStateWithLifecycle()


    BuyScreen(
        uiState = uiState,
        modifier = modifier,
        buyingTokenInfo = viewModel.getBuyingTokenInfo(),
        amount = viewModel.buyingTokenAmount,
        onAmountUpdate = viewModel::updateAmount,
        amountInDollar = viewModel.buyingTokenAmountUSD,
        thirdPartyProvider = viewModel.thirdPartyBuyingProvider,
        tokenList = tokenList,
        setOriginInfo = viewModel::setBuyingTokenInfo,
    )
}


@Composable
fun BuyScreen(
    uiState: BuyUiState,
    modifier: Modifier = Modifier,
    buyingTokenInfo: TokenInfo?,
    amount: Double,
    onAmountUpdate: KFunction1<String, Unit>,
    amountInDollar: String,
    thirdPartyProvider: List<ThirdPartyProvider>,
    tokenList: List<TokenInfo>,
    setOriginInfo: KFunction1<TokenInfo, Unit>,
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
                setOriginInfo.invoke(it)
            },
        )
    }
    LazyColumn(
        modifier = modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {

            TokenSelectingBox(
                onClickAction = {confirmDialogShowingState = true},
                value = buyingTokenInfo?.name ?: "",
                hint = stringResource(id =R.string.select_token),
                label = stringResource(id = R.string.buy_title).uppercase(),
                leadingIcon = {
                    Icon(
                        painter = rememberAsyncImagePainter(buyingTokenInfo?.logoUrl),
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
        }
        item {
            MainEditText(
                label = stringResource(id = R.string.token_amount).uppercase(),
                value = amount.toString(),
                onValueChange = onAmountUpdate,
                descriptionIcon = {
                    Text(modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                        .alpha(0.5f),text = amountInDollar, )
                })
        }
        item {

            Text(
                modifier= Modifier.padding(horizontal = 15.dp),
                text = stringResource(id = R.string.buy_system), color = MaterialTheme.colors.primary, style = MaterialTheme.typography.caption)
        }
        items(thirdPartyProvider) { thirdPartyProvider ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 2.dp,
                        shape = RectangleShape,
                        color = MaterialTheme.colors.secondary
                    )
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp),
                    painter = rememberAsyncImagePainter(thirdPartyProvider.logoUrl),
                    contentDescription = thirdPartyProvider.name
                )
                Column(Modifier.weight(1f).padding(vertical = 10.dp)) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = thirdPartyProvider.name,
                        style = MaterialTheme.typography.caption,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = thirdPartyProvider.description,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15))
                        .background(MaterialTheme.colors.primaryVariant)
                        .padding(2.dp),
                    text = "Best rate",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .width(15.dp)
                        .width(15.dp)
                        .rotate(180f),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                )

            }
        }

    }




    when (uiState) {
        BuyUiState.Nothing -> {

        }
    }
}


