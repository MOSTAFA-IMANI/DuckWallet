package com.akaam.app.duckwallet.ui.theme

import android.service.autofill.OnClickAction
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val cardRoundCornerRadiusValue = 20.dp
private val inputTextRoundCornerRadiusValue = 25.dp

@Composable
fun TokenSelectingBox(
    onClickAction: ()->Unit,
    modifier: Modifier = Modifier,
    value: String="",
    selectionTextStyle: TextStyle = LocalTextStyle.current,
    labelTextStyle: TextStyle = LocalTextStyle.current,
    hintTextStyle: TextStyle = LocalTextStyle.current,
    label: String = "",
    hint: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    descriptionIcon: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(inputTextRoundCornerRadiusValue),

    ) {

    Surface(
        shape = RoundedCornerShape(cardRoundCornerRadiusValue), modifier = modifier
    ) {
        Column(

            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(2.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical =  5.dp),
                    text = label,
                    color = MaterialTheme.colors.onSurface,
                    style = labelTextStyle,
                    fontSize = 14.sp)
                if (descriptionIcon != null) descriptionIcon()
            }
            Spacer(modifier = Modifier.padding(1.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 2.dp)
                            .clip(shape)
                            .background(MaterialTheme.colors.background)
                            .padding(10.dp, 10.dp)
                            .clickable {
                                onClickAction()
                            },

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (leadingIcon != null) leadingIcon()
                        Box(Modifier.weight(1f)) {
                            if (value.isEmpty()) {
                                Text(
                                    fontSize = 10.sp,
                                    modifier = Modifier.alpha(0.3f),
                                    text = hint,
                                    style = hintTextStyle
                                )
                            }
                            Text(
                                modifier = Modifier,
                                fontSize = 10.sp,
                                text = value,
                                style = selectionTextStyle,
                                color =  MaterialTheme.colors.onSurface,
                            )
                        }
                        if (trailingIcon != null) trailingIcon()
                    }


            Spacer(modifier = Modifier.padding(2.dp))
        }

    }


}

@Preview
@Composable
fun PreviewSelectingBox() {
    DuckWalletTheme() {

        TokenSelectingBox(
            onClickAction = {},
            value = "",
            label = "testLable",
            trailingIcon = { Icon(imageVector = Icons.Filled.AccountBox, contentDescription = null) },
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        descriptionIcon = { Column(Modifier.padding(horizontal = 30.dp, vertical = 5.dp)) {
            Text(text = "testDescription1")
            Text(text = "testDescription2")

        }})
    }
}

