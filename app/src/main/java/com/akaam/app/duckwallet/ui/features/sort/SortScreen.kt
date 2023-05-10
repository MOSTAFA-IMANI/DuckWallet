

package com.akaam.app.duckwallet.ui.features.sort


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ClickableText
import com.akaam.app.duckwallet.ui.theme.MainButton


@Composable
fun SortRoute(
    sortByNameOnClick : ()->Unit,
    sortByVolumeOnClick : ()->Unit,
    sortByDateOnClick : ()->Unit,
    modifier: Modifier = Modifier,
    viewModel: SortViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SortByScreen(
        uiState = uiState,
        sortByNameOnClick = sortByNameOnClick,
        sortByVolumeOnClick = sortByVolumeOnClick,
        sortByDateOnClick = sortByDateOnClick,
        modifier = modifier
    )
}


@Composable
fun SortByScreen(
    uiState: SortUiState,
    sortByNameOnClick : ()->Unit,
    sortByVolumeOnClick : ()->Unit,
    sortByDateOnClick : ()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(text = stringResource(id = R.string.sort_by_name), onclick = sortByNameOnClick)
        ClickableText(text = stringResource(id = R.string.sort_by_volume), onclick = sortByVolumeOnClick)
        ClickableText(text = stringResource(id = R.string.sort_by_date), onclick = sortByDateOnClick)
                    Spacer(Modifier.weight(1f))
    }

    when (uiState) {
        SortUiState.Nothing ->{}
        SortUiState.SortByDateClicked ->{}
        SortUiState.SortByNameClicked ->{}
        SortUiState.SortByVolumeClicked ->{}
    }
}


