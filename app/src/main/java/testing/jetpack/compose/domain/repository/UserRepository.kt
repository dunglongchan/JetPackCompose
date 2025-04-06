package testing.jetpack.compose.domain.repository

import kotlinx.coroutines.flow.Flow
import testing.jetpack.compose.domain.model.AuthResult
import testing.jetpack.compose.domain.model.UserLoginForm

interface UserRepository {

    fun actionUserLogin(userLoginForm: UserLoginForm): Flow<AuthResult>

    fun actionUserRegister(userLoginForm: UserLoginForm): Flow<AuthResult>

}