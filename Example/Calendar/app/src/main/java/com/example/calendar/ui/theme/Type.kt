package com.example.calendar.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

enum class TypoType {
    Title, HeadLine, Body, BodyBold, BodyBoldSmall
}

@Composable
fun typography(textStyle: TypoType): TextStyle {
    return when (textStyle) {
        TypoType.Title -> TextStyle(
            fontWeight = FontWeight.Black,
            fontSize = 18.sp,
        )
        TypoType.Body -> TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        TypoType.BodyBold -> TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        TypoType.BodyBoldSmall -> TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )

        else -> TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
    }
}