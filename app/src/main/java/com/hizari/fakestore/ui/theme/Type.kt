package com.hizari.fakestore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.hizari.fakestore.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Manrope")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.ExtraBold),
)

val Typography = Typography(
    displayLarge = Typography().displayLarge.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
    ),
    displayMedium = Typography().displayMedium.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    displaySmall = Typography().displaySmall.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    headlineLarge = Typography().headlineLarge.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    headlineMedium = Typography().headlineMedium.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    headlineSmall = Typography().headlineSmall.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    titleLarge = Typography().titleLarge.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = Typography().titleMedium.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    titleSmall = Typography().titleSmall.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    bodyMedium = Typography().bodyMedium.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    bodySmall = Typography().bodySmall.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    labelLarge = Typography().labelLarge.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    labelMedium = Typography().labelMedium.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    ),
    labelSmall = Typography().labelSmall.copy(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
)