package com.akaam.app.duckwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.rememberNavController
import com.akaam.app.duckwallet.ui.navigation.DuckWalletNavgraph
import com.akaam.app.duckwallet.ui.theme.DuckWalletTheme
import com.akaam.app.duckwallet.ui.theme.SnackbarDelegate
import dagger.hilt.android.AndroidEntryPoint

val LocalDestination = compositionLocalOf<MutableState<String>> { error("todo for later usage") }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val destination = remember {
                mutableStateOf( value = "")
            }
            val scaffoldState = rememberScaffoldState()
            val snackbarDelegate: SnackbarDelegate = remember {
                SnackbarDelegate(scaffoldState.snackbarHostState)
            }


            snackbarDelegate.apply {
                snackbarHostState = scaffoldState.snackbarHostState
                coroutineScope = rememberCoroutineScope()
            }
            DuckWalletTheme {
                val navController = rememberNavController()
                val generalStartAndEndPadding = dimensionResource(id = R.dimen.general_start_and_end_padding)
                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        SnackbarHost(hostState = it) {snackbarData->
                            val backgroundColor = snackbarDelegate.snackbarBackgroundColor
                            Snackbar(snackbarData = snackbarData, backgroundColor = backgroundColor)
                        }
                    },
                    topBar = {/*TopBar(destination.value)*/}
                ) {padding->

                    DuckWalletNavgraph(
                        onFailureOccurred = {},
                        screenModifiers = Modifier.padding(
                            vertical = padding.calculateTopPadding(),
                            horizontal = generalStartAndEndPadding),
                        navController = navController,
                        destinationLabel = destination,
                    )
                }
            }
        }
    }







}



