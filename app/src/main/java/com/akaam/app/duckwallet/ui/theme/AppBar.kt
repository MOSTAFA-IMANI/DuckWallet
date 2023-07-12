package com.akaam.app.duckwallet.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akaam.app.duckwallet.R


private val roundCornerRadiusValue = 20.dp
@Composable
fun DuckTopAppBar(
    title:String,
    onNavigationIconClick:()->Unit={},
    navigationIcon: @Composable (() -> Unit)? = {
        IconButton(onClick = { onNavigationIconClick() }) {
            androidx.compose.material.Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
        }
    },
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    @DrawableRes detailIcon:  Int? = null,
    contentColor: Color = contentColorFor(backgroundColor),
) = Surface(     ) {

//      CompositionLocalProvider(LocalDestination provides destination) {

    val topBarHeight = dimensionResource(id = R.dimen.top_bar_height)

    TopAppBar(
        modifier = Modifier
            .height(topBarHeight)
            .fillMaxWidth()
      ,
        title = {
            Text(text = title)
        },
        navigationIcon = navigationIcon,
        backgroundColor = backgroundColor,
        contentColor= contentColor,
        elevation = 10.dp

        )
    Image( modifier = Modifier
        .height(topBarHeight)
        .fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
        painter = painterResource(id = R.drawable.background_top_bar),
        contentDescription = "")

    detailIcon?.let {
        Image( modifier = Modifier
            .height(topBarHeight)
            .fillMaxWidth()
            .padding(8.dp),
            colorFilter = ColorFilter.tint(contentColor),
            contentScale = ContentScale.Fit,
            alignment = Alignment.CenterEnd,
            painter = painterResource(id =it),
            contentDescription = "")
    }



}

@Preview
@Composable
fun PreviewAppbar() {
    DuckTopAppBar("aksdfhka")

}

