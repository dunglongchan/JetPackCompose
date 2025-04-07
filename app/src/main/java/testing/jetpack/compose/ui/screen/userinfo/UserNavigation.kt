package testing.jetpack.compose.ui.screen.userinfo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import testing.jetpack.compose.domain.model.LocalUser

const val USER_SCREEN_ROUTE = "user_detail"

fun NavGraphBuilder.userScreenDestination(user: LocalUser) {
    composable(route = USER_SCREEN_ROUTE) {
        UserScreen(user)
    }
}

fun NavController.navigationToUser(
    navOptions: NavOptions? = null
) {
    navigate(
        route = USER_SCREEN_ROUTE,
        navOptions = navOptions
    )
}