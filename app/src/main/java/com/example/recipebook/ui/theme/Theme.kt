
package com.example.recipebook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = SushiRed,
    onPrimary = White,
    secondary = SakuraPink,
    onSecondary = DarkText,
    tertiary = DarkRed,
    background = SoftWhite,
    onBackground = DarkText,
    surface = White,
    onSurface = DarkText,
    surfaceVariant = SoftGray,
    onSurfaceVariant = DarkText
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkRed,
    onPrimary = DarkBackground,
    secondary = DarkPink,
    onSecondary = LightText,
    tertiary = SakuraPink,
    background = DarkBackground,
    onBackground = LightText,
    surface = DarkSurface,
    onSurface = LightText,
    surfaceVariant = DarkPink,
    onSurfaceVariant = LightText
)

@Composable
fun RecipeBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
