package com.akaam.app.duckwallet.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign

private val cardRoundCornerRadiusValue = 20.dp
private val inputTextRoundCornerRadiusValue = 25.dp

@Composable
fun MnemonicCodeItem(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = CircleShape,

    ) {

    val backgroundColor = if (readOnly || !enabled) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.surface
    }
    val textColor = if (readOnly || !enabled) {
        MaterialTheme.colors.onPrimary
    } else {
        MaterialTheme.colors.onBackground
    }
    Card(
        modifier = Modifier
            .padding(2.dp)
            .aspectRatio(1f)
            .clip(shape)
            .background(backgroundColor),
        elevation = 10.dp,
        shape = shape,

//        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor),
            contentAlignment = Alignment.Center,

            ) {

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier
                    .padding(2.dp, 2.dp)
                    .align(Alignment.Center)
                    .background(backgroundColor),
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle.copy(textAlign = TextAlign.Center, color = textColor),
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                singleLine = singleLine,
                maxLines = maxLines,
                interactionSource = interactionSource,

                )
        }


    }


}

@Composable
fun MnemonicCode(
    codeList: List<String>,
    onValueChange : (String,Int)->Unit={_,_->},
    enabled: Boolean = true,
    readOnly: Boolean = false,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(codeList.size) {

            val current = codeList[it]
            MnemonicCodeItem(
                value = current,
                onValueChange ={content->
                    onValueChange(content,it)} ,
                enabled = enabled,
                readOnly = readOnly)

        }
    }
}

@Preview
@Composable
fun PreviewIMnemonicCodeEditText() {
    repeat(12) {

    }
    DuckWalletTheme() {
        MnemonicCode(listOf("1d",
            "2d",
            "3d",
            "4d",
            "5dsdfsdfsdfd",
            "5dd",
            "7sd",
            "91213456789987456321",
            "10"),
            enabled = false, readOnly = true, )
//        MnemonicCodeItem(value = "value", onValueChange = {})
    }
}

