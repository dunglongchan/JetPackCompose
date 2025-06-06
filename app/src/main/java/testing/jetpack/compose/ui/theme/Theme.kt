package testing.jetpack.compose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)


@Composable
fun JetPackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme
        }

        darkTheme -> CustomDarkColorScheme
        else -> CustomLightColorScheme
    }
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
    ) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}

object JetPackComposeTheme {
    val colors: AppColorScheme
        @Composable
        get() = LocalAppColorScheme.current
}
