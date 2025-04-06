package testing.jetpack.compose.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import testing.jetpack.compose.R


data class AppColorScheme(
    val primary: Color,
    val primaryRed: Color,
    val primaryGreen: Color,
    val primaryYellow: Color,
    val primaryOrange: Color,
    val primaryBlack: Color,
    val primaryWhite: Color,

    val inkHeadline: Color,
    val inkBody: Color,
    val inkDescription: Color,
    val inkDisabled: Color,
    val inkButton: Color,

    val backGround01: Color,
    val backGround02: Color,
    val backGround03: Color,

    val opacity01: Color,
    val opacity02: Color,
    val opacity03: Color,

)

data class AppTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,

    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,

    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle
)

val InterFont = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)

)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        primary = Color.Unspecified,
        primaryRed = Color.Unspecified,
        primaryGreen = Color.Unspecified,
        primaryYellow = Color.Unspecified,
        primaryOrange = Color.Unspecified,
        primaryBlack = Color.Unspecified,
        primaryWhite = Color.Unspecified,
        inkHeadline = Color.Unspecified,
        inkBody = Color.Unspecified,
        inkDescription = Color.Unspecified,
        inkDisabled = Color.Unspecified,
        inkButton = Color.Unspecified,
        backGround01 = Color.Unspecified,
        backGround02 = Color.Unspecified,
        backGround03 = Color.Unspecified,
        opacity01 = Color.Unspecified,
        opacity02 = Color.Unspecified,
        opacity03 = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default,

        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,

        labelLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelSmall = TextStyle.Default
    )
}
