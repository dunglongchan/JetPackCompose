package testing.jetpack.compose.data

import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.domain.model.UserLoginForm

class ServerUserDataSource {
    fun getAllUser() = null
    fun login(loginForm: UserLoginForm): LocalUser? {
        return null
    }

    fun register(userName: String, password: String) = null

    fun getUserByUserName(userName: String): LocalUser? {
        return null
    }

    fun getUserById(userID: Int) = null
}