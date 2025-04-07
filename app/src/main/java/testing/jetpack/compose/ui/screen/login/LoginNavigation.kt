package testing.jetpack.compose.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import testing.jetpack.compose.domain.model.LocalUser

const val LOGIN_ROUTE = "login"

fun NavGraphBuilder.loginDestination(
    onLoginSuccess: (LocalUser?) -> Unit = {}
) {
    composable(LOGIN_ROUTE) {
        LoginScreen(){
            onLoginSuccess.invoke(it)
        }
    }
}

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    navigate(LOGIN_ROUTE, navOptions)
}