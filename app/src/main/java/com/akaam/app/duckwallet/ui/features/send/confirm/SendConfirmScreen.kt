package com.akaam.app.duckwallet.ui.features.send.confirm


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.send.SendViewModel
import com.akaam.app.duckwallet.ui.theme.ActionScaffold
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.PasswordConfirmDialog


@Composable
fun SendConfirmRoute(
    modifier: Modifier = Modifier,
    viewModel: SendViewModel = hiltViewModel(),
    onNextStepClick: () -> Unit,
) {
    val uiState by viewModel.uiStateConfirm.collectAsStateWithLifecycle()


    SendConfirmScreen(
        uiState = uiState,
        modifier = modifier,
        onNextStepClick = onNextStepClick
    )
}


@Composable
fun SendConfirmScreen(
    uiState: SendConfirmUiState,
    modifier: Modifier = Modifier,
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
                Toast.makeText(context, "Send After checking Password", Toast.LENGTH_SHORT)
                    .show()
            },
            onDismiss = {})
    }


    ActionScaffold(appBarTitle = stringResource(id = R.string.screen_title_send).uppercase(),
    actionContent = {


        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 30.dp, end = 30.dp),
            onClick = { onNextStepClick.invoke()
                confirmDialogShowingState = true},
            text = stringResource(id = R.string.confirm_button_title).uppercase(),
            isTheMainBottomButton = true,
            isSecondory = true
        )

    }) {
        MainContentToSend()

    }

    when (uiState) {
        SendConfirmUiState.Nothing -> {}
    }
}

@Composable
private fun MainContentToSend() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "walletName",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "MainNet",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.send_button_title),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onSurface
        )
        Image(
            modifier = Modifier
                .width(160.dp),
            painter = painterResource(id = R.drawable.ic_send_confirm),
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val boxModifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.surface)
                .wrapContentWidth()
                .weight(0.5f)
                .padding(5.dp)
            FromBox(boxModifier)
            ArrowIcon()
            ToBox(boxModifier)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.resource_consumed),
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = "=",
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = stringResource(id = R.string.bandwith),
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
fun ArrowIcon() {
    Image(
        modifier = Modifier.padding(5.dp),
        painter = painterResource(id = R.drawable.ic_arow),
        contentDescription = null)
}

@Composable
fun ToBox(boxModifier: Modifier) {
    Card(
        modifier = boxModifier,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.send_to).uppercase(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(92.dp))
    }
}

@Composable
fun FromBox(boxModifier: Modifier) {
    Card(
        modifier = boxModifier,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.send_from).uppercase(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(92.dp))
    }
}

