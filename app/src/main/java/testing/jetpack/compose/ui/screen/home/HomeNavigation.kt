package testing.jetpack.compose.ui.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import testing.jetpack.compose.domain.model.LocalUser

const val HOME_PREFIX = "home"
const val HOME_USER_NAME = "userName"
const val HOME_ROUTE = "$HOME_PREFIX/{$HOME_USER_NAME}"


data class HomeArgs(val user: LocalUser) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        Gson().fromJson(
            checkNotNull(savedStateHandle[HOME_USER_NAME]) as String,
            LocalUser::class.java
        )
    )
}

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
    userName: LocalUser?,
    navOptions: NavOptions? = null
) {
    val jsonString = Gson().toJson(userName)

    navigate(
        route = "$HOME_PREFIX/$jsonString",
        navOptions = navOptions
    )
}