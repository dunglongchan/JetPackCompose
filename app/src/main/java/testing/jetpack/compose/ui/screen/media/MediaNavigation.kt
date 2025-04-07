package testing.jetpack.compose.ui.screen.media

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val MEDIA_ROUTE = "media"

fun NavGraphBuilder.mediaDestination() {
    composable(MEDIA_ROUTE) {
        MediaScreen()
    }
}

fun NavController.navigateToMedia(navOptions: NavOptions? = null) {
    navigate(MEDIA_ROUTE, navOptions)
}