package testing.jetpack.compose.ui.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SPLASH_ROUTE: String = "splash"

fun NavGraphBuilder.splashDestination(onSplashScreenFinished: () -> Unit) {
    composable(SPLASH_ROUTE) {
        SplashScreen(onSplashScreenFinished)
    }
}

fun NavController.navigateToSplash(
    navOptions: NavOptions? = null,
) {
    navigate(SPLASH_ROUTE, navOptions)
}