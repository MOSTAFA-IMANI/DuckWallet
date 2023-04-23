package com.akaam.app.duckwallet.ui.features.verifymnemonic

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow

private const val TAG = "MnemonicCodeScreen"

@Composable
fun VerifyMnemonicCodeRoute(
    navigateToHome: () -> Unit,
    viewModel: VerifyMnemonicViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle(VerifyMnemonicCodeUiState.Loading)
    val watchListState by viewModel.selectedMnemonicCodeList.collectAsStateWithLifecycle()
    val inputList by viewModel.inputMnemonicCodeList.collectAsStateWithLifecycle()

    VerifyMnemonicCodeScreen(
        uiState = state,
        watchListState = watchListState,
        inputList = inputList,
        onItemClicked = viewModel::onItemClicked,
        onDoneButtonClick = viewModel::checkInsertedCode,
        navigateToHome = navigateToHome,
        resetUiState = viewModel::resetUiState,
                modifier = modifier,

    )
}

@Composable
internal fun VerifyMnemonicCodeScreen(

    uiState: VerifyMnemonicCodeUiState,
    watchListState: List<String>,
    onItemClicked: (String, Int) -> Unit,
    navigateToHome: () -> Unit,
    inputList: List<String>,
    onDoneButtonClick: () -> Unit,
    resetUiState: (Boolean) -> Unit,
    modifier: Modifier,

    ) {

    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainContent(
            watchListState = watchListState,
            inputList = inputList,
            onItemClicked = onItemClicked,
        )

        MainButton(onClick = onDoneButtonClick,
            text = stringResource(id = R.string.done_button_title),
            isTheMainBottomButton = true,
            isSecondory = true)
        BottomSpacer()
    }

    when (uiState) {
        VerifyMnemonicCodeUiState.EmptyInputException -> {
            Toast.makeText(context,
                stringResource(id = R.string.error_empty_inputs),
                Toast.LENGTH_SHORT).show()
            resetUiState(false)

        }
        VerifyMnemonicCodeUiState.Loading -> {

        }
        VerifyMnemonicCodeUiState.NavigateToHome -> {
            navigateToHome()
        }
        VerifyMnemonicCodeUiState.Nothing -> Unit
        VerifyMnemonicCodeUiState.IncorrectInputException -> {
            Toast.makeText(context,
                stringResource(id = R.string.error_incorrect_order_for_code),
                Toast.LENGTH_SHORT).show()
            resetUiState(true)

        }
    }

}

@Composable
fun ColumnScope.MainContent(
    watchListState: List<String>,
    inputList: List<String>,
    onItemClicked: (String, Int) -> Unit,
) {

    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        MnemonicWatchList(
            mnemonicCodeList = watchListState,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MnemonicCodeInputSelection(
            inputSelectionList = inputList,
            onItemClicked = onItemClicked,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(style = Typography.body2,
            text = stringResource(id = R.string.verify_mnemonic_code_description),
            color = MaterialTheme.colors.onSurface)
    }
}

@Composable
fun MnemonicCodeInputSelection(
    inputSelectionList: List<String>,
    onItemClicked: (String, Int) -> Unit,
) {
    FlowRow {
        inputSelectionList.forEach {

            FlatButton(
                modifier = Modifier.wrapContentWidth(),
                onClick = { onItemClicked(it, 0) },
                text = it,
                fontSize = 11.sp)
        }
    }

    /* LazyVerticalGrid(
         columns = GridCells.Fixed(3),
         modifier = Modifier
             .fillMaxWidth(),
         verticalArrangement = Arrangement.Center,

         ) {
         items(inputSelectionList.size) {
             Column(horizontalAlignment = Alignment.CenterHorizontally) {
                 val current = inputSelectionList[it]
                 FlatButton(
                     modifier = Modifier.wrapContentWidth(),
                     onClick = { onItemClicked(current, it) },
                     text = current,
                     fontSize = 11.sp)
             }
         }*/


}


@Composable
fun MnemonicWatchList(
    mnemonicCodeList: List<String>,
) {

    Spacer(modifier = Modifier.height(8.dp))
    MnemonicCode(codeList = mnemonicCodeList, enabled = true, readOnly = true)


}
