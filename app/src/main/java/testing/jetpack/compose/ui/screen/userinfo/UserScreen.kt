package testing.jetpack.compose.ui.screen.userinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.ui.theme.JetPackComposeTheme

@Composable
fun UserScreen(
    user: LocalUser
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(JetPackComposeTheme.colors.backGround01)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Hello, ${user.userInfo.fullName}")
            Text(user.userInfo.email)
            Text(user.userInfo.phone)
            Text(user.userInfo.jobTitle)
            Text(user.userInfo.dateOfBirth)
            Text(user.loginForm.userName)
        }

    }
}