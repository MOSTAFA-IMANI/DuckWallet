package com.akaam.app.duckwallet.ui.features.pairledger.root


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.BottomSpacer
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.Typography
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold


@Composable
fun PairLedgerRoute(
    navigateToAddNewDevice: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PairLedgerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentTutorialPage by viewModel.currentTutorialPage.collectAsStateWithLifecycle()

    PairLedgerScreen(
        uiState = uiState,
        navigateToAddNewDevice = navigateToAddNewDevice,
        onNextButtonClicked = viewModel::onNextClicked,
        currentTutorialPage = currentTutorialPage,
        modifier = modifier
    )
}


@Composable
fun PairLedgerScreen(
    uiState: PairLedgerUiState,
    navigateToAddNewDevice: () -> Unit,
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,
    currentTutorialPage: Int,
) {

    WelcomeScaffold(
        appBarTitle = stringResource(id = R.string.screen_title_pair_ledger).uppercase(),
        actionContent = {
            MainButton(onClick = { onNextButtonClicked() },
                text = stringResource(id = R.string.next_step),
                isTheMainBottomButton = true,
                isSecondory = true)
        }
    ){
        MainTutorialContent(currentTutorialPage)

    }

    when (uiState) {
        PairLedgerUiState.Nothing, PairLedgerUiState.Loading -> Unit
        PairLedgerUiState.NavigateToAddNewDevice -> navigateToAddNewDevice()

    }
}

@Composable
fun MainTutorialContent(currentTutorialPage: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val tutorialImageId: Int
        val tutorialTextId: Int
        when (currentTutorialPage) {
            1 -> {
                tutorialImageId = R.drawable.ic_pair_ledger_tutorial_1
                tutorialTextId = R.string.text_pair_ledger_tutorial_1
            }
            2 -> {
                tutorialImageId = R.drawable.ic_pair_ledger_tutorial_2
                tutorialTextId = R.string.text_pair_ledger_tutorial_2

            }
            else -> {
                tutorialImageId = -1
                tutorialTextId = -1
            }
        }
        if (tutorialImageId != -1 && tutorialTextId != -1) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(style = Typography.body2,
                text = stringResource(id = tutorialTextId),
                color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.padding(10.dp))
            Image(modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f),

                painter = painterResource(id = tutorialImageId), contentDescription = "")

        }
        Spacer(modifier = Modifier.height(10.dp))
        val indexSize = 32.dp
        val isFirstSelected= currentTutorialPage==1
        val selectedModifier = Modifier
            .width(indexSize)
            .height(indexSize)
            .padding(2.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
        val unSelectedModifier = Modifier
            .width(indexSize)
            .height(indexSize)
            .padding(2.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colors.primary, CircleShape)


        Row {
            Text(
                modifier = if(isFirstSelected)selectedModifier else unSelectedModifier,
                text = "1",
                textAlign = TextAlign.Center,
                color = if(isFirstSelected) MaterialTheme.colors.surface else   MaterialTheme.colors.primary)
            Text(
                modifier =if(isFirstSelected) unSelectedModifier else selectedModifier,
                text = "2",
                textAlign = TextAlign.Center,
                color = if(isFirstSelected) MaterialTheme.colors.primary else   MaterialTheme.colors.surface )
        }
        Spacer(modifier = Modifier.height(10.dp))


    }
}


