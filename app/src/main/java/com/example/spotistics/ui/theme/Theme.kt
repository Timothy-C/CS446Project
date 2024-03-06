package com.example.spotistics.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Zomp,
    secondary = Emerald,
    secondaryVariant = LightGreen,
    surface = Eggplant
)

private val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Zomp,
    secondary = Emerald,
    secondaryVariant = LightGreen,
    surface = Eggplant
)

//@Composable
//fun SpotisticsTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
//
//    MaterialTheme(
//        colors = colors,
//        content = content,
//        typography = Typography
//    )
//}