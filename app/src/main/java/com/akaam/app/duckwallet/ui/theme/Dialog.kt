package com.akaam.app.duckwallet.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.theme.icon.DuckWalletIcons
import com.akaam.app.duckwallet.ui.theme.icon.Icon


private val roundCornerRadiusValue = 20.dp

@Composable
fun PasswordConfirmDialog(
    value: String = "",
    setShowDialog: (Boolean) -> Unit,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,

    ) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {

        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .clip(RoundedCornerShape(16.dp))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.enter_password).uppercase(),
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary
                )


                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primaryVariant),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = txtField.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {
                        txtField.value = it.take(10)
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    trailingIcon = {
                        val image =
                            if (isPasswordVisible) DuckWalletIcons.Visibility else DuckWalletIcons.VisibilityOff

                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            image.ToIcon()
                        }
                    })

                Spacer(modifier = Modifier.height(90.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    MainButton(
                        modifier = Modifier.wrapContentWidth(),
                        onClick = {
                            setShowDialog(false)
                            onDismiss.invoke()
                        },
                        text = stringResource(id = R.string.cancel_buuton_title).uppercase(),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor = MaterialTheme.colors.onSurface,

                            )
                    )
                    MainButton(
                        modifier = Modifier.wrapContentWidth(),
                        onClick = {
                            if (txtField.value.isEmpty()) {
                                txtFieldError.value = "Field can not be empty"
                                return@MainButton
                            }
                            onConfirm(txtField.value)
                            setShowDialog(false)
                        },
                        text = stringResource(id = R.string.confirm_button_title).uppercase(),
                        isSecondory = true,
                        isTheMainBottomButton = false
                    )
                }

            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FullScreenTokenListDialog(
    value: String = "",
    setShowDialog: (Boolean) -> Unit,
    onConfirm: (TokenInfo) -> Unit,
    tokenList: List<TokenInfo>,
) {
    val txtField = remember { mutableStateOf(value) }
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { setShowDialog(false) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .background(MaterialTheme.colors.background)
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        ) {

            item {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primaryVariant),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = txtField.value,
                    onValueChange = {
                        txtField.value = it.take(10)
                    },
                    singleLine = true,
                    trailingIcon = {
                        val image = Icon.ImageVectorIcon(Icons.Filled.Search)

                        IconButton(onClick = {}) {
                            image.ToIcon()
                        }
                    })
            }
            itemsIndexed(tokenList) { index, item ->
                TokenList(tokenList, item, index
                ) {
                    onConfirm.invoke(item)
                    setShowDialog(false)
                }
            }


        }
    }
}

@Composable
private fun TokenList(
    tokenList: List<TokenInfo>,
    item: TokenInfo,
    index: Int,
    onClick: () -> Unit,
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO: load from web image logo
        Image(
            painter = rememberAsyncImagePainter(item.logoUrl),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(0.dp, 5.dp),
        )
        Text(
            text = item.name,
            style = MaterialTheme.typography.overline
        )
        Text(
            text = item.price.toString(),
            style = MaterialTheme.typography.overline
        )
        Text(
            text = "${item.amount}  =${item.amountInUSD} $",
            style = MaterialTheme.typography.overline
        )
    }
    if (index < tokenList.lastIndex)
        Divider(
            modifier = Modifier.alpha(0.1f),
            color = MaterialTheme.colors.onSurface
        )


}


@Preview
@Composable
fun PreviewDialog() {
    MainButton({}, "title")
    FlatButton({}, "copy")
}

