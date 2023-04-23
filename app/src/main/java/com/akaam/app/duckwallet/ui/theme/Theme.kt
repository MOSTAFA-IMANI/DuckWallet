package com.akaam.app.duckwallet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = blue500,
    primaryVariant = blue200,
    secondary = green500,
    surface = gray500,
    onSurface = brown500,
    background = Color.White,
    onBackground= Color.Black,
    error = red500

    )

private val LightColorPalette = lightColors(
    primary = blue500,
    primaryVariant = blue200,
    secondary = green500,
    surface = gray500,
    onSurface = brown500,
    background = Color.White,
    onBackground= Color.Black,
    error = red500

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun DuckWalletTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                    content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}