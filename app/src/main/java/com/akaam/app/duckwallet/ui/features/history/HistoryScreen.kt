package com.akaam.app.duckwallet.ui.features.history


import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ClickableText


@Composable
fun HistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HistoryScreen(
        uiState = uiState,
        modifier = modifier,
    allTabClicked = viewModel::onAllClicked,
    sendTabClicked = viewModel::onSendClicked,
    receiveTabClicked = viewModel::onReceiveClicked,
    stakeTabClicked = viewModel::onStakeClicked,
    )
}


@Composable
fun HistoryScreen(
    uiState: HistoryUiState,
    allTabClicked:()->Unit,
    sendTabClicked:()->Unit,
    receiveTabClicked:()->Unit,
    stakeTabClicked:()->Unit,
    modifier: Modifier = Modifier,
   ) {
    val columnTitleModifier = Modifier.wrapContentWidth()

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 5.dp, vertical = 16.dp),

    ) {
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
            ClickableText(modifier = columnTitleModifier ,text = stringResource(id = R.string.history_tab_all).uppercase(), onclick =allTabClicked )
            ClickableText(modifier = columnTitleModifier ,text = stringResource(id = R.string.history_tab_send).uppercase(), onclick =sendTabClicked )
            ClickableText(modifier = columnTitleModifier ,text = stringResource(id = R.string.history_tab_receive).uppercase(), onclick =receiveTabClicked )
            ClickableText(modifier = columnTitleModifier ,text = stringResource(id = R.string.history_tab_stake).uppercase(), onclick =stakeTabClicked )
        }
        Divider(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),color = MaterialTheme.colors.primary, thickness = 1.dp)

    }

    when (uiState) {
        HistoryUiState.AllTabSelected ->{}
        HistoryUiState.Nothing ->{}
        HistoryUiState.ReceiveTabSelected ->{}
        HistoryUiState.SendTabSelected ->{}
        HistoryUiState.StakeTabSelected ->{}
    }
}



