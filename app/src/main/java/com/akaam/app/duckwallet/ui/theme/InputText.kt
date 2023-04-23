package com.akaam.app.duckwallet.ui.theme

import android.content.res.Resources.Theme
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akaam.app.duckwallet.ui.util.extension.innerShadow
import  androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults.BorderBox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor

private val cardRoundCornerRadiusValue = 20.dp
private val inputTextRoundCornerRadiusValue = 25.dp

@Composable
fun MainEditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String = "",
    hint: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(inputTextRoundCornerRadiusValue),

) {

Surface(shape =  RoundedCornerShape(cardRoundCornerRadiusValue),
   ) {
    Column( modifier = Modifier
        .background(MaterialTheme.colors.surface)
        .padding(2.dp),) {
        Text(
            modifier = Modifier.padding(8.dp,2.dp),
            text = label,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp)
        Spacer(modifier = Modifier.padding(1.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .clip(shape)
                .background(MaterialTheme.colors.background)
                .padding(10.dp,10.dp),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
//            placeholder = placeholder,
//            leadingIcon = leadingIcon,
//            trailingIcon = trailingIcon,
//            isError = isError,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
//            colors = colors,
            decorationBox = @Composable { innerTextField ->
                Row(
                    modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                fontSize= 10.sp,
                                modifier = Modifier.alpha(0.2f),
                                text = hint,
                            )
                        }
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }

            }
            )
    /*    OutlinedTextField(
            shape = shape,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp)
                .clip(shape)
                .background(MaterialTheme.colors.background)
               ,

            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            colors = colors,
            value = value,
            onValueChange = onValueChange,

            )*/
        Spacer(modifier = Modifier.padding(2.dp))
    }

}


}

@Preview
@Composable
fun PreviewInputText() {
    DuckWalletTheme() {

        MainEditText(value = "value", onValueChange = {}, label = "test")
    }
}

