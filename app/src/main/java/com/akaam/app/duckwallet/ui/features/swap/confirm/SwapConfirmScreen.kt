package com.akaam.app.duckwallet.ui.features.swap.confirm


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.swap.SwapViewModel
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.PasswordConfirmDialog


@Composable
fun SwapConfirmRoute(
    modifier: Modifier = Modifier,
    viewModel: SwapViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,
) {
    val uiState by viewModel.confirmUiState.collectAsStateWithLifecycle()


    SwapSelectionScreen(
        uiState = uiState,
        modifier = modifier,
        originTokenInfo = viewModel.getOriginInfo(),
        destinationTokenInfo = viewModel.getDestinationInfo(),
        onNextStepClick = onNextStepClick
    )
}


@Composable
fun SwapSelectionScreen(
    uiState: SwapConfirmUiState,
    modifier: Modifier = Modifier,
    originTokenInfo: TokenInfo?,
    destinationTokenInfo: TokenInfo?,
    onNextStepClick: () -> Unit,
) {
    var confirmDialogShowingState by remember {
        mutableStateOf(false)
    }
    if (confirmDialogShowingState) {
        val context = LocalContext.current
        PasswordConfirmDialog(
            setShowDialog = {
                confirmDialogShowingState = it
            },
            onConfirm = {
                Toast.makeText(context, "Swap After checking Password", Toast.LENGTH_SHORT)
                    .show()
            },
            onDismiss = {})
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TransactionIcon()

        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp),
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
                val textModifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)

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
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.swap_confirm_description),
            style = MaterialTheme.typography.caption
        )

        Divider(
            modifier = Modifier
                .alpha(0.5f)
                .padding(horizontal = 5.dp),
            color = MaterialTheme.colors.onSurface,
            thickness = 1.dp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.resource_consumed),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = stringResource(id = R.string.bandwith, "0.00").uppercase(),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary
            )

        }
        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 30.dp, end = 30.dp),
            onClick = { onNextStepClick.invoke()
                confirmDialogShowingState = true},
            text = stringResource(id = R.string.swap_button_title),
            isTheMainBottomButton = true,
            isSecondory = true
        )


    }





    when (uiState) {
        SwapConfirmUiState.Nothing -> {

        }
    }
}

@Composable
fun TransactionIcon() {
    val totalWidth = 150.dp
    val tokenWidth = totalWidth / 2
    Image(
        modifier = Modifier
            .width(totalWidth - tokenWidth)
            .height(totalWidth - tokenWidth)
            .rotate(90f),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondary),
        painter = painterResource(id = R.drawable.ic_round_arrow),
        contentDescription = null
    )
    Row(
        modifier = Modifier.width(totalWidth + tokenWidth),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .width(tokenWidth)
                .height(tokenWidth),
            painter = painterResource(id = R.drawable.ic_test_token1),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .width(tokenWidth)
                .height(tokenWidth),
            painter = painterResource(id = R.drawable.ic_test_token2),
            contentDescription = null
        )
    }
    Image(
        modifier = Modifier
            .width(tokenWidth)
            .height(tokenWidth)
            .rotate(270f),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondary),
        painter = painterResource(id = R.drawable.ic_round_arrow),
        contentDescription = null
    )
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


