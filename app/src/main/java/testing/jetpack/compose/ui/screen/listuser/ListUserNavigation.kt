package testing.jetpack.compose.ui.screen.listuser

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val LIST_USER_ROUTE = "list_user"

fun NavGraphBuilder.listUserDestination() {
    composable(LIST_USER_ROUTE) {
        ListUserScreen()
    }
}

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    navigate(LIST_USER_ROUTE, navOptions)
}