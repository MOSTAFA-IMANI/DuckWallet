package com.akaam.app.duckwallet.ui.features.invite


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ProfileScaffold

val boxCornerRadius = 22.dp

@Composable
fun InviteRoute(
    modifier: Modifier = Modifier,
    viewModel: InviteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val creditCount by viewModel.credit.collectAsStateWithLifecycle()
    val invitedCount by viewModel.invitedCount.collectAsStateWithLifecycle()
    val invitationCode by viewModel.invitationCode.collectAsStateWithLifecycle()

    InviteScreen(
        uiState = uiState,
        creditCount = creditCount,
        invitedCount = invitedCount,
        invitationCode = invitationCode,
        modifier = modifier
    )
}


@Composable
fun InviteScreen(
    uiState: InviteUiState,
    creditCount: Int,
    invitedCount: Int,
    invitationCode: String,
    modifier: Modifier = Modifier,
) {
    ProfileScaffold(appBarTitle = stringResource(id = R.string.screen_title_invite).uppercase()) {

        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement =  Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {

            item {
                Text(text = stringResource(id = R.string.invite_friend_share),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h4)
                ShareInviteBox(creditCount, invitedCount, invitationCode)
            }
            item {
                BestRankingBox()
            }
            item {
                RewardRulesBox()
            }
            item {
                UsageOfCreditsBox()
            }
        }
    }

    when (uiState) {
        InviteUiState.Nothing -> {}
    }
}

@Composable
fun UsageOfCreditsBox() {
    Card(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 140.dp),
        contentColor = Color.Black,
        backgroundColor = Color(0xFFA3F8D9),
        shape = RoundedCornerShape(boxCornerRadius)
    ) {
        Column(Modifier.padding(10.dp)) {

            Text(text = stringResource(id = R.string.usage_credits_box_title),
                style = MaterialTheme.typography.body2,
                color = Color.Black)

        }
    }

}

@Composable
fun BestRankingBox() {

    Card(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 140.dp),
        contentColor = Color.Black,
        backgroundColor = Color(0xFFA6D8FF),
        shape = RoundedCornerShape(boxCornerRadius)
    ) {
        Column(Modifier.padding(10.dp)) {

            Text(text = stringResource(id = R.string.best_ranking_box_title),
                style = MaterialTheme.typography.body2,
                color = Color.Black)
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(id = R.string.address),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = stringResource(id = R.string.credit),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface

                )
            }
        }
    }

}

@Composable
fun RewardRulesBox() {

    Card(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 220.dp),
        contentColor = Color.Black,
        backgroundColor = Color(0xFFCDF8BA),
    shape = RoundedCornerShape(boxCornerRadius)
    ) {
        Column (Modifier.padding(10.dp)){

            Text(text = stringResource(id = R.string.reward_rules_box_title),
                style = MaterialTheme.typography.body2,
                color = Color.Black)

        }
    }

}

@Composable
fun ShareInviteBox(creditCount: Int, invitedCount: Int, invitationCode: String) {
    val localDensity = LocalDensity.current

    // Create element height in pixel state
    var columnHeightPx by remember {
        mutableStateOf(0f)
    }

    // Create element height in dp state
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    columnHeightPx = coordinates.size.height.toFloat()
                    columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                },
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.ic_box_friend_invite),
            contentDescription = null)
        Column(
            modifier = Modifier.padding(top = columnHeightDp / 2 + 15.dp, start = 15.dp, end = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            val modifierForText = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
            Text(
                modifier = modifierForText,
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.credits, creditCount),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption)
            Text(
                modifier = modifierForText,
                textAlign = TextAlign.Center,
                color = Color.White,
                text = stringResource(id = R.string.friend_invited_msg, invitedCount),
                style = MaterialTheme.typography.overline)
            Text(
                modifier = modifierForText.padding(top = 7.dp, bottom = 0.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                text = stringResource(id = R.string.my_invitation_code),
                style = MaterialTheme.typography.body2,
            )

            InvitationCodeBox(invitationCode)
            Text(
                modifier = modifierForText,
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.refer_friend_msg),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.overline,)
        }
    }
}

@Composable
fun InvitationCodeBox(invitationCode: String) {
    val wordLengthForChunked = invitationCode.length / 4
    val chunkList = invitationCode.chunked(wordLengthForChunked)
    val imageModifier = Modifier.height(45.dp).width(15.dp)
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Spacer(modifier = imageModifier)
        for (chunk in chunkList) {
            Card(modifier = Modifier
                .wrapContentWidth()
                .padding(3.dp),
                shape = RoundedCornerShape(boxCornerRadius),
                contentColor = Color.Black,
                backgroundColor = Color(0xFFEBEDAC)) {
                Text(modifier = Modifier.padding(2.dp),text = chunk, style = MaterialTheme.typography.overline)
            }
        }
        Image(modifier = imageModifier,
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.ic_copy),
            alignment = Alignment.TopCenter,
            contentDescription = stringResource(id = R.string.copy_button_title))

    }

}


