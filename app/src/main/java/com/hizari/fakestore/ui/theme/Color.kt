package com.hizari.fakestore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryBlue = Color(0xFF802C6E)
val SecondaryBlue = Color(0xFFA6398F)
val LightBackground = Color(0xFFF0F0F0)
val DarkBackground = Color(0xFF1C1C1C)
val LightSurface = Color.White
val DarkSurface = Color(0xFF2C2C2C)
val OnPrimaryLight = Color.White
val OnPrimaryDark = Color.White
val OnSecondaryLight = Color.Black
val OnSecondaryDark = Color.Black
val OnBackgroundLight = Color.Black
val OnBackgroundDark = Color.White
val OnSurfaceLight = Color.Black
val OnSurfaceDark = Color.White
val TertiaryColor = Color(0xFFCCCCCC)
val SuccessLight = Color(0xFF4CAF50)
val SuccessDark = Color(0xFF388E3C)
val SuccessContainer = Color(0xFFE7F5EB)
val WarningLight = Color(0xFFFFC107)
val WarningDark = Color(0xFFFFA000)
val WarningContainer = Color(0xFFFFF8E3)

@Composable
fun defaultOutlinedTextFieldColors(): TextFieldColors {
    val mainColor = if (isSystemInDarkTheme()) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.primary
    }

    return OutlinedTextFieldDefaults.colors().copy(
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        focusedIndicatorColor = mainColor,
        focusedLabelColor = mainColor,
        unfocusedContainerColor = Color.Transparent,
    )
}
