package testing.jetpack.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import testing.jetpack.compose.ui.screen.home.homeDestination
import testing.jetpack.compose.ui.screen.home.navigateToHome
import testing.jetpack.compose.ui.screen.login.loginDestination
import testing.jetpack.compose.ui.screen.login.navigateToLogin
import testing.jetpack.compose.ui.screen.splash.SPLASH_ROUTE
import testing.jetpack.compose.ui.screen.splash.splashDestination

@Composable
fun RootNavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        splashDestination() { navController.navigateToLogin() }
        loginDestination() { user ->
            navController.navigateToHome(user)
        }
        homeDestination()
    }
}