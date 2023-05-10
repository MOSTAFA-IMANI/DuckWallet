package com.akaam.app.duckwallet.ui.theme

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


private val roundCornerRadiusValue = 20.dp

@Composable
fun ShowErrorMessage(message:String, isShortMessage:Boolean=false){
    val context = LocalContext.current
    if (isShortMessage)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    else
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
@Composable
fun ShowToastMessage(message:String, isShortMessage:Boolean=false){
    val context = LocalContext.current
    if (isShortMessage)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    else
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}