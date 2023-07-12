package com.akaam.app.duckwallet.ui.theme

import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun WelcomeScaffold(
    modifier: Modifier = Modifier,
    appBarTitle: String,
    actionContent: @Composable() (() -> Unit)? = null,
    @DrawableRes detailTopBarIcon: Int? = null,
    isScrollable: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    localBackHandler: OnBackPressedDispatcherOwner? = LocalOnBackPressedDispatcherOwner.current,
    content: @Composable () -> Unit,
) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
        DuckTopAppBar(title = appBarTitle,
            onNavigationIconClick = {
                localBackHandler?.onBackPressedDispatcher?.onBackPressed()
            },
        detailIcon = detailTopBarIcon)
    },
    bottomBar = actionContent ?: {}
) { padding ->
    if (isScrollable) {
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    } else {
        modifier
            .fillMaxSize()


    }

    Column(
        modifier = modifier
            .padding(padding)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {

        Box(modifier = Modifier.weight(1f)) {
            content.invoke()
        }
        Spacer(modifier = Modifier.height(12.dp))
//            actionContent?.invoke()

        BottomSpacer()
    }

}


@Composable
fun ActionScaffold(
    modifier: Modifier = Modifier,
    appBarTitle: String,
    actionContent: @Composable() (() -> Unit)? = null,
    isScrollable: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    localBackHandler: OnBackPressedDispatcherOwner? = LocalOnBackPressedDispatcherOwner.current,
    content: @Composable () -> Unit,
) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
        DuckTopAppBar(
            title = appBarTitle,
            onNavigationIconClick = {
                localBackHandler?.onBackPressedDispatcher?.onBackPressed()
            },
            backgroundColor = MaterialTheme.colors.onSurface,
            contentColor = MaterialTheme.colors.surface
        )
    },
    bottomBar = actionContent ?: {}
) { padding ->
    if (isScrollable) {
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    } else {
        modifier
            .fillMaxSize()


    }

    Column(
        modifier = modifier
            .padding(padding)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {

        Box(modifier = Modifier.weight(1f)) {
            content.invoke()
        }
        Spacer(modifier = Modifier.height(12.dp))
//            actionContent?.invoke()

        BottomSpacer()
    }

}


@Composable
fun ProfileScaffold(
    modifier: Modifier = Modifier,
    appBarTitle: String,
    actionContent: @Composable() (() -> Unit)? = null,
    isScrollable: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    localBackHandler: OnBackPressedDispatcherOwner? = LocalOnBackPressedDispatcherOwner.current,
    content: @Composable () -> Unit,
) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
        DuckTopAppBar(
            title = appBarTitle,
            onNavigationIconClick = {
                localBackHandler?.onBackPressedDispatcher?.onBackPressed()
            },
            backgroundColor = Color(0xFF828282),
            contentColor = Color.White
        )
    },
    bottomBar = actionContent ?: {}
) { padding ->
    if (isScrollable) {
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    } else {
        modifier
            .fillMaxSize()


    }

    Column(
        modifier = modifier
            .padding(padding)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {

        Box(modifier = Modifier.weight(1f)) {
            content.invoke()
        }
        Spacer(modifier = Modifier.height(12.dp))
//            actionContent?.invoke()

        BottomSpacer()
    }

}

@Preview
@Composable
fun PreviewWelcomeScaffold() {
    ProfileScaffold(Modifier, "aksdfhka", { MainButton(onClick = { }, text = "test") }, true) {
        Column {
            for (i in 0..100)
                Text("hasgdhas asdg $i")
        }
    }

}

