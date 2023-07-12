

package com.akaam.app.duckwallet.ui.features.stacking

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.theme.ProfileScaffold


@Composable
fun StakingRoute(
    modifier: Modifier = Modifier,

) {
    StakingScreen(
        modifier = modifier
    )
}

@Composable
internal fun StakingScreen(
    modifier: Modifier = Modifier,
) {

    ProfileScaffold(appBarTitle = stringResource(id = R.string.screen_title_staking).uppercase()) {

    }
}