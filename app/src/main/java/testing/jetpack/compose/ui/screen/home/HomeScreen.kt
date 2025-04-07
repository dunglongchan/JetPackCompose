package testing.jetpack.compose.ui.screen.home

import android.graphics.drawable.Icon
import android.os.Parcelable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import testing.jetpack.compose.ui.navigation.HomeNavigationStack
import testing.jetpack.compose.ui.screen.listuser.LIST_USER_ROUTE
import testing.jetpack.compose.ui.screen.media.MEDIA_ROUTE
import testing.jetpack.compose.ui.screen.userinfo.USER_SCREEN_ROUTE
import testing.jetpack.compose.ui.theme.BackGround01
import testing.jetpack.compose.ui.theme.JetPackComposeTheme
import testing.jetpack.compose.ui.util.MyCustomNavBarItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeNavController: NavHostController = rememberNavController()
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        contentColor = JetPackComposeTheme.colors.backGround01,
        containerColor = JetPackComposeTheme.colors.backGround01,
        bottomBar = { MyBottomBar(homeNavController) }
    ) { innerPadding ->
        HomeNavigationStack(
            user = viewModel.localUser, modifier = Modifier
                .consumeWindowInsets(WindowInsets.navigationBars)
                .consumeWindowInsets(WindowInsets.ime)
                .padding(innerPadding),
            homeNavController
        )
    }
}

@Composable
fun MyBottomBar(
    navController: NavHostController
) {
    BottomAppBar(
        containerColor = BackGround01,
    ) {
        val destinations = listOf(
            HomeScreenTab.User,
            HomeScreenTab.ListUser,
            HomeScreenTab.Media
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        destinations.forEach { destination ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route == destination.route
            } == true

            MyCustomNavBarItem(
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                },
                label = destination.label,
                isSelected = isSelected
            ) {
                navController.navigate(destination.route)
            }
        }
    }
}

private sealed class HomeScreenTab() {
    abstract val icon: ImageVector
    abstract val label: @Composable (() -> Unit)?
    abstract val route: String


    data object User : HomeScreenTab() {
        override val icon: ImageVector
            get() = Icons.Default.Person
        override val label: @Composable() (() -> Unit)
            get() = { Text("User") }

        override
        val route: String
            get() = USER_SCREEN_ROUTE
    }

    data object ListUser : HomeScreenTab() {
        override val icon: ImageVector
            get() = Icons.Default.CheckCircle
        override val label: @Composable() (() -> Unit)
            get() = { Text("List User") }
        override val route: String
            get() = LIST_USER_ROUTE
    }

    data object Media : HomeScreenTab() {
        override val icon: ImageVector
            get() = Icons.Default.PlayArrow
        override val label: @Composable() (() -> Unit)
            get() = { Text("Media") }
        override val route: String
            get() = MEDIA_ROUTE
    }
}

