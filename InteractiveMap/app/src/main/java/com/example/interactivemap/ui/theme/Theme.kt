package com.example.interactivemap.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkPrimary,
    primaryVariant = Color.White,
    secondary = DarkPrimary,
    background = DarkBackground,
    onSurface = DarkSecondary

)

private val LightColorPalette = lightColors(
    primary = VSTUBlue,
    primaryVariant = Color.Black,
    secondary = Color.Black,
    background = Color.White,
    onSurface = SearchBarColor
    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun InteractiveMapTheme(
    darkTheme: Boolean,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
    ) {
        content()
    }
}