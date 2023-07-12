package com.akaam.app.duckwallet.ui.features.notification


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
import com.akaam.app.duckwallet.ui.theme.WelcomeScaffold


@Composable
fun NotificationRoute(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        uiState = uiState,
        modifier = modifier)
}


@Composable
fun NotificationScreen(
    uiState: NotificationUiState,
    modifier: Modifier = Modifier,
   ) {
    val columnTitleModifier = Modifier.wrapContentWidth()
    WelcomeScaffold(appBarTitle = stringResource(id = R.string.screen_title_notification).uppercase(),
        detailTopBarIcon = R.drawable.ic_mail
    )
    {
        Column(
            modifier = modifier.fillMaxSize().padding(horizontal = 5.dp, vertical = 16.dp),

            ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                ClickableText(modifier = columnTitleModifier.padding(8.dp) ,
                    text = stringResource(id = R.string.no_message),
                    onClick ={} )
            }
            Divider(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),color = MaterialTheme.colors.primary, thickness = 1.dp)

        }
    }

    when (uiState) {
        NotificationUiState.Nothing -> {

        }
    }
}




