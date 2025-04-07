package testing.jetpack.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.ui.screen.listuser.listUserDestination
import testing.jetpack.compose.ui.screen.media.mediaDestination
import testing.jetpack.compose.ui.screen.userinfo.USER_SCREEN_ROUTE
import testing.jetpack.compose.ui.screen.userinfo.userScreenDestination

@Composable
fun HomeNavigationStack(
    user: LocalUser,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = USER_SCREEN_ROUTE,
        modifier = modifier
    ) {
        userScreenDestination(user)
        listUserDestination()
        mediaDestination()
    }
}