package testing.jetpack.compose.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val LOGIN_ROUTE = "login"

fun NavGraphBuilder.loginDestination() {
    composable(LOGIN_ROUTE) {
        LoginScreen()
    }
}

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    navigate(LOGIN_ROUTE, navOptions)
}