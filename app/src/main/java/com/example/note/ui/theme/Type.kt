package com.example.note.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.note.R
import com.example.note.presentation.screen.setting.FontSize

val SahelFont = FontFamily(Font(R.font.sahel))

fun getTypography(fontSize: FontSize): Typography {
    val baseSize = when (fontSize) {
        FontSize.SMALL -> 12
        FontSize.MEDIUM -> 16
        FontSize.LARGE -> 20
    }

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = SahelFont,
            fontWeight = FontWeight.Normal,
            fontSize = baseSize.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = SahelFont,
            fontWeight = FontWeight.Normal,
            fontSize = (baseSize - 2).sp
        ),
        bodySmall = TextStyle(
            fontFamily = SahelFont,
            fontWeight = FontWeight.Normal,
            fontSize = (baseSize - 4).sp
        ),
        titleLarge = TextStyle(
            fontFamily = SahelFont,
            fontWeight = FontWeight.Bold,
            fontSize = (baseSize + 4).sp
        ),
        titleMedium = TextStyle(
            fontFamily = SahelFont,
            fontWeight = FontWeight.Bold,
            fontSize = (baseSize + 2).sp
        ),
        titleSmall = TextStyle(
            fontFamily = SahelFont,
            fontWeight = FontWeight.Medium,
            fontSize = baseSize.sp
        )
    )
}