package com.akaam.app.duckwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
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
                    topBar = {TopBar(destination)}
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




    @Composable
    fun TopBar(destination: MutableState<String>) = Surface() {

//      CompositionLocalProvider(LocalDestination provides destination) {

        val topBarHeight = dimensionResource(id = R.dimen.top_bar_height)

          TopAppBar(
              modifier = Modifier
                  .height(topBarHeight)
                  .fillMaxWidth(),
              title = {
                  Text(text = destination.value/*navController.currentDestination?.route?:""*/)
              },
              navigationIcon = {
                  IconButton(onClick = { onBackPressed() }) {
                      Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                  }
              },

              )
          Image( modifier = Modifier
              .height(topBarHeight)
              .fillMaxWidth(),
              contentScale = ContentScale.FillWidth,
              painter = painterResource(id = R.drawable.background_top_bar),
              contentDescription = "")



    } 
    


}



