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
    displayLarge = Typography().displayLarge.copy(fontFamily= fontFamily),
    displayMedium = Typography().displayMedium.copy(fontFamily = fontFamily),
    displaySmall = Typography().displaySmall.copy(fontFamily = fontFamily),
    headlineLarge = Typography().headlineLarge.copy(fontFamily = fontFamily),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = fontFamily),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = fontFamily),
    titleLarge = Typography().titleLarge.copy(fontFamily = fontFamily),
    titleMedium = Typography().titleMedium.copy(fontFamily = fontFamily),
    titleSmall = Typography().titleSmall.copy(fontFamily = fontFamily),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = fontFamily),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = fontFamily),
    bodySmall = Typography().bodySmall.copy(fontFamily = fontFamily),
    labelLarge = Typography().labelLarge.copy(fontFamily = fontFamily),
    labelMedium = Typography().labelMedium.copy(fontFamily = fontFamily),
    labelSmall = Typography().labelSmall.copy(fontFamily = fontFamily)
)