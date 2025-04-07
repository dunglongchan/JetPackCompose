package testing.jetpack.compose.domain.repository

import kotlinx.coroutines.flow.Flow
import testing.jetpack.compose.domain.model.AuthResult
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.domain.model.UserLoginForm

interface UserRepository {

    fun actionUserLogin(userLoginForm: UserLoginForm): Flow<AuthResult>

    fun getAllUser(): List<LocalUser>

    fun actionUserRegister(userLoginForm: UserLoginForm): Flow<AuthResult>

}