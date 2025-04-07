package testing.jetpack.compose.ui.util

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.ui.theme.JetPackComposeTheme


@Composable
fun DoubleBackPressHandler() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isBackPressed = remember { mutableStateOf(false) }

    BackHandler(!isBackPressed.value) {
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        scope.launch {
            delay(2000)
            isBackPressed.value = false
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    textInputStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLine: Int = 1,
    color: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    isError: Boolean = false,
    onFocusState: (Boolean) -> Unit = {}
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (!isPasswordVisible) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "",
                tint = JetPackComposeTheme.colors.primary
            )
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = if (label.isNotEmpty()) {
            { Text(text = label) }
        } else null,
        placeholder = { Text(text = placeholder) },
        textStyle = textInputStyle,
        maxLines = maxLine,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                onFocusState(focus.hasFocus)
            },
        colors = color,
        visualTransformation = if (visualTransformation is PasswordVisualTransformation) {
            if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
        } else visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = if (visualTransformation is PasswordVisualTransformation) trailingIcon else null,
        isError = isError
    )
}

@Composable
fun RowScope.MyCustomNavBarItem(
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit)? = null,
    isSelected: Boolean = false,
    onTabSelected: () -> Unit
) {
    NavigationBarItem(
        icon = icon,
        label = label,
        selected = isSelected,
        onClick = {
            onTabSelected.invoke()
        }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = JetPackComposeTheme.colors.primary,
            selectedIconColor = JetPackComposeTheme.colors.inkButton,
            unselectedIconColor = JetPackComposeTheme.colors.inkHeadline,
            selectedTextColor = JetPackComposeTheme.colors.primary,
            unselectedTextColor = JetPackComposeTheme.colors.inkHeadline,
        )

    )
}

@Composable
fun ShowUserInfo(user: LocalUser) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Hello, ${user.userInfo.fullName}")
        Text(user.userInfo.email)
        Text(user.userInfo.phone)
        Text(user.userInfo.jobTitle)
        Text(user.userInfo.dateOfBirth)
        Text(user.loginForm.userName)
    }
}