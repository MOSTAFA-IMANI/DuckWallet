package com.akaam.app.duckwallet.ui.theme

import android.text.style.ClickableSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akaam.app.duckwallet.ui.util.extension.innerShadow


private val roundCornerRadiusValue = 20.dp
val WhiteColorWithAlpha  = Color(0x30FFFFFF)
@Composable
fun MainButton(
    onClick: () -> Unit,
    text: String,
    textSize: TextUnit =20.sp,
    descriptionSize: TextUnit =9.sp,
    description: String? = null,
    isSecondory: Boolean = false,
    isTheMainBottomButton:Boolean=false,
    modifier: Modifier = if (isTheMainBottomButton)
        Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 30.dp, start = 30.dp, end = 40.dp)
    else
        Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = null,
    shape: Shape = RoundedCornerShape(roundCornerRadiusValue),
    border: BorderStroke? = ButtonDefaults.outlinedBorder,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = Color.White,
        backgroundColor = if (isSecondory) MaterialTheme.colors.secondary
        else MaterialTheme.colors.primary),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,

    ) {


    OutlinedButton(
        modifier =
        modifier
            .innerShadow(
            color = WhiteColorWithAlpha,
            cornersRadius = roundCornerRadiusValue,
            blur = 20.dp,
            spread = 0.dp,
            offsetX = 0.dp,
            offsetY = 9.dp)
            ,

        enabled = enabled,
        interactionSource = interactionSource,
        elevation =ButtonDefaults.elevation(
            defaultElevation = 3.dp,
            pressedElevation = 5.dp,
            hoveredElevation = 4.dp,
            focusedElevation = 4.dp
        ),
        border = border,
        contentPadding = contentPadding,
        onClick = onClick,
        shape = shape,
        colors = colors) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = textSize,
                text = text)
            description?.let {
                Text(
                    fontSize = descriptionSize,
                    text = description)
            }
        }
    }

}
@Composable
fun FlatButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = null,
    shape: Shape = RoundedCornerShape(roundCornerRadiusValue),
    border: BorderStroke? = ButtonDefaults.outlinedBorder,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.onSurface,
        backgroundColor =  MaterialTheme.colors.surface),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    fontSize: TextUnit = 14.sp,
    textAlign :TextAlign = TextAlign.Center
) {
    OutlinedButton(
        modifier =
        modifier.padding(1.dp),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation =elevation,
        border = border,
        contentPadding = contentPadding,
        onClick = onClick,
        shape = shape,
        colors = colors) {

        Text(

            fontSize = fontSize,
            text = text,
            textAlign = textAlign)

    }

}
@Composable
fun MenuButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = null,
    shape: Shape = RoundedCornerShape(roundCornerRadiusValue),
    border: BorderStroke? = ButtonDefaults.outlinedBorder,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.onSurface,
        backgroundColor =  MaterialTheme.colors.surface),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    fontSize: TextUnit = 14.sp,
    textAlign :TextAlign = TextAlign.Center
) {
    OutlinedButton(
        modifier =
        modifier.padding(1.dp),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation =elevation,
        border = border,
        contentPadding = contentPadding,
        onClick = onClick,
        shape = shape,
        colors = colors) {

        Text(
            modifier= Modifier.fillMaxWidth(),
            fontSize = fontSize,
            text = text,
            textAlign = textAlign)

    }

}

@Composable
fun ClickableText(text:String, onclick:()->Unit,color:Color = MaterialTheme.colors.primary){
    Text(modifier =
    Modifier.clickable { onclick.invoke() }
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 16.dp),
        text = text,
        color = color,
        textAlign = TextAlign.Start)
}

@Preview
@Composable
fun PreviewMain() {
    MainButton({}, "title")
    FlatButton({},"copy")
}


@Preview
@Composable
fun PreviewSecondory() {
    MainButton({}, text="title", description = "description",)
}