package testing.jetpack.compose.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import testing.jetpack.compose.ui.navigation.RootNavigationStack
import testing.jetpack.compose.ui.theme.JetPackComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme() {
                RootNavigationStack()
            }
        }
    }
}
