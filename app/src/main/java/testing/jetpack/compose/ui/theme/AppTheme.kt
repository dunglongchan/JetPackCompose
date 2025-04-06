package testing.jetpack.compose.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

 val CustomDarkColorScheme = AppColorScheme(
    primary = PrimaryColorNight,
    primaryRed = PrimaryRed,
    primaryGreen = PrimaryGreen,
    primaryYellow = PrimaryYellow,
    primaryOrange = PrimaryOrange,
    primaryBlack = PrimaryBlack,
    primaryWhite = PrimaryWhite,

    inkHeadline = InkHeadlineNight,
    inkBody = InkBodyNight,
    inkDescription = InkDescriptionNight,
    inkDisabled = InkDisabledNight,
    inkButton = InkButtonNight,

    backGround01 = BackGround01Night,
    backGround02 = BackGround02Night,
    backGround03 = BackGround03Night,

    opacity01 = Opacity01,
    opacity02 = Opacity02,
    opacity03 = Opacity03
)

 val CustomLightColorScheme = AppColorScheme(
    primary = PrimaryColor,
    primaryRed = PrimaryRed,
    primaryGreen = PrimaryGreen,
    primaryYellow = PrimaryYellow,
    primaryOrange = PrimaryOrange,
    primaryBlack = PrimaryBlack,
    primaryWhite = PrimaryWhite,

    inkHeadline = InkHeadline,
    inkBody = InkBody,
    inkDescription = InkDescription,
    inkDisabled = InkDisabled,
    inkButton = InkButton,

    backGround01 = BackGround01,
    backGround02 = BackGround02,
    backGround03 = BackGround03,

    opacity01 = Opacity01,
    opacity02 = Opacity02,
    opacity03 = Opacity03
)

private val CustomTypography = AppTypography(
    titleLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> CustomDarkColorScheme
        else -> CustomLightColorScheme
    }

    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides CustomTypography,
        LocalIndication provides rippleIndication,
        content = content
    )
}

object AppTheme {
    val colors: AppColorScheme
        @Composable
        get() = LocalAppColorScheme.current
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}