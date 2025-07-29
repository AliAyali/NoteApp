package com.example.note.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun NoteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    primaryColor: androidx.compose.ui.graphics.Color,
    typography: Typography,
    content: @Composable (() -> Unit),
) {

    val lightColorScheme = lightColorScheme(
        primary = primaryColor,
        secondary = LightSecondaryGray,
        tertiary = LightTertiaryYellow,
        background = LightBackground,
        surface = LightSurface
    )

    val darkColorScheme = darkColorScheme(
        primary = primaryColor,
        secondary = DarkSecondaryGray,
        tertiary = DarkTertiaryYellow,
        background = DarkBackground,
        surface = DarkSurface
    )

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
