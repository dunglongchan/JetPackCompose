package testing.jetpack.compose.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val HOME_PREFIX = "home"
const val HOME_USER_NAME = "userName"
const val HOME_ROUTE = "$HOME_PREFIX/{$HOME_USER_NAME}"

fun NavGraphBuilder.homeDestination() {
    composable(route = HOME_ROUTE,
        arguments = listOf(
            navArgument(HOME_USER_NAME) {
                type = NavType.StringType
            }
        )
    ) {
        HomeScreen()
    }
}

fun NavController.navigateToHome(
    userName: String = "",
    navOptions: NavOptions? = null
) {
    navigate(
        route = "$HOME_PREFIX/$userName",
        navOptions = navOptions
    )
}