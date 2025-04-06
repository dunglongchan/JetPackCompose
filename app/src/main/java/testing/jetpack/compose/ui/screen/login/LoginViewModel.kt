package testing.jetpack.compose.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import testing.jetpack.compose.domain.model.AuthResult
import testing.jetpack.compose.domain.model.UserLoginForm
import testing.jetpack.compose.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var isLogging = false

    var username by mutableStateOf("nguyenvana")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    var password by mutableStateOf("Password123!")
        private set

    fun updatePassword(input: String) {
        password = input
    }

    private val _authResult = MutableSharedFlow<AuthResult>()
    val authResult: SharedFlow<AuthResult> = _authResult

    fun actionLogin() {
        if (isLogging) return
        viewModelScope.launch {
            isLogging = true
            login()
        }
    }

    private suspend fun login() {

        val userLoginForm = UserLoginForm(username, password)

        if (userLoginForm.isEmpty()) {
            _authResult.emit(AuthResult.Error("Username or password is empty"))
            isLogging = false
            return
        }
        if (!userLoginForm.isLegalPassword()) {
            _authResult.emit(AuthResult.Error("Password is incorrect form"))
            isLogging = false
            return
        }

        userRepository.actionUserLogin(userLoginForm).collect {
            if (it is AuthResult.Success || it is AuthResult.Error) isLogging = false
            _authResult.emit(it)
        }
    }
}