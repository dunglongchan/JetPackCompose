package testing.jetpack.compose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LocalUser(
    val userID: Int = 0,
    val userInfo: UserInformation,
    val loginForm: UserLoginForm
)

@Serializable
data class UserLoginForm(
    val userName: String,
    val password: String
) {
    fun isEmpty() = userName.isEmpty() || password.isEmpty()

    fun isLegalPassword() =
        password.length >= 6

}

@Serializable
data class UserInformation(
    val fullName: String,
    val dateOfBirth: String,
    val email: String,
    val phone: String,
    val jobTitle: String
)

sealed class AuthResult() {
    data object None : AuthResult()
    data class Success(val user: LocalUser) : AuthResult()
    data class VerifyingUser(val process: Int) : AuthResult()
    data class Error(val error: String) : AuthResult()
}

const val PASSWORD_REGEX_SYNTAX =
    "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#\$%^&*(),.?\":{}|<>]{8,}$"