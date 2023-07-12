package com.akaam.app.duckwallet.ui.features.search


import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.PlaneEditText
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold


@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        uiState = uiState,
        modifier = modifier,
    searchedValue = viewModel.searchedValue,
    onSearchValueChanged = viewModel::onSearchValueChanged)
}


@Composable
fun NotificationScreen(
    uiState: SearchUiState,
    modifier: Modifier = Modifier,
    searchedValue: String,
    onSearchValueChanged: (String)->Unit
   ) {
    val columnTitleModifier = Modifier.wrapContentWidth()
    WelcomeScaffold(appBarTitle = stringResource(id = R.string.screen_title_search).uppercase(),
        detailTopBarIcon = R.drawable.ic_search
    )
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp, vertical = 16.dp),

            ) {
            PlaneEditText(value = searchedValue,
                onValueChange = onSearchValueChanged,
                hint = stringResource(id = R.string.search_box_hint),
                leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) })

            Divider(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),color = MaterialTheme.colors.primary, thickness = 1.dp)

        }
    }

    when (uiState) {
        SearchUiState.Nothing -> {

        }
    }
}




