package testing.jetpack.compose.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import testing.jetpack.compose.domain.model.AuthResult
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.ui.theme.JetPackComposeTheme
import testing.jetpack.compose.ui.theme.PrimaryColor
import testing.jetpack.compose.ui.theme.PrimaryGreen
import testing.jetpack.compose.ui.theme.PrimaryRed
import testing.jetpack.compose.ui.theme.PrimaryYellow
import testing.jetpack.compose.ui.util.CustomTextField
import testing.jetpack.compose.ui.util.DoubleBackPressHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (LocalUser?) -> Unit = {}
) {
    DoubleBackPressHandler()

    Scaffold(
        containerColor = JetPackComposeTheme.colors.backGround01,
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = JetPackComposeTheme.colors.backGround01,
                    titleContentColor = JetPackComposeTheme.colors.inkHeadline,
                ),
                title = {
                    Text(
                        "Login",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
            )
        }
    ) { innerPadding ->

        val scrollState = rememberScrollState()
        val gradientColors = listOf(PrimaryColor, PrimaryRed, PrimaryYellow, PrimaryGreen)

        val textSessionStyle = MaterialTheme.typography.headlineSmall.copy(
            brush = Brush.linearGradient(gradientColors)
        )

        val textInputStyle = MaterialTheme.typography.bodyMedium.copy(
            brush = Brush.linearGradient(gradientColors)
        )

        var isLoginError by remember { mutableStateOf(false) }
        var loginProcess by remember { mutableIntStateOf(0) }
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.authResult.collect { authResult ->

                isLoginError = authResult is AuthResult.Error

                when (authResult) {
                    is AuthResult.Error -> {
                        val error = (authResult as? AuthResult.Error)?.error
                        Log.e("hahaha", "LoginScreen: $error")
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }

                    is AuthResult.Success -> {
                        // navigate to screen
                        val user = (authResult as? AuthResult.Success)?.user
                        onLoginSuccess.invoke(user)
                    }

                    is AuthResult.VerifyingUser -> {
                        val process = (authResult as? AuthResult.VerifyingUser)?.process
                        loginProcess = process ?: 0
                    }

                    AuthResult.None -> {}
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Username", style = textSessionStyle)

            CustomTextField(
                value = viewModel.username,
                onValueChange = { viewModel.updateUsername(it) },
                label = "Username",
                placeholder = "Enter your username",
                textInputStyle = textInputStyle,
                color = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = JetPackComposeTheme.colors.primary,
                    unfocusedBorderColor = JetPackComposeTheme.colors.inkDescription
                )
            )
            Text("Password", style = textSessionStyle)

            CustomTextField(
                value = viewModel.password,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = "Enter your password",
                textInputStyle = textInputStyle,
                color = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = JetPackComposeTheme.colors.primary,
                    unfocusedBorderColor = JetPackComposeTheme.colors.inkDescription,
                    errorBorderColor = JetPackComposeTheme.colors.primaryRed,
                    errorContainerColor = JetPackComposeTheme.colors.primaryBlack,
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = isLoginError,
                onFocusState = { isFocus ->
                    if (isFocus && isLoginError) isLoginError = false
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.actionLogin()
                },
                colors = ButtonColors(
                    JetPackComposeTheme.colors.primary,
                    JetPackComposeTheme.colors.inkButton,
                    JetPackComposeTheme.colors.primary,
                    JetPackComposeTheme.colors.primary
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(52.dp),
            ) {
                Text("Login${if (loginProcess != 0) " $loginProcess" else ""}")
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    JetPackComposeTheme {
        LoginScreen()
    }
}