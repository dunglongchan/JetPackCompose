package testing.jetpack.compose.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import testing.jetpack.compose.data.LocalUserDataSource
import testing.jetpack.compose.data.ServerUserDataSource
import testing.jetpack.compose.domain.model.AuthResult
import testing.jetpack.compose.domain.model.UserLoginForm
import testing.jetpack.compose.domain.repository.UserRepository

class UserImpl(
    private val localUserDataSource: LocalUserDataSource,
    private val serverUserDataSource: ServerUserDataSource
) : UserRepository {
    override fun actionUserLogin(userLoginForm: UserLoginForm): Flow<AuthResult> = channelFlow {

        var process = 0
        var status: AuthResult = AuthResult.None

        val processJob = launch {
            while (process <= 100) {
                if (process >= 80 && (status is AuthResult.Success || status is AuthResult.Error)) {
                    delay(5L)
                } else {
                    delay(10L)
                }
                send(AuthResult.VerifyingUser(process))
                process += 1
            }
        }

        val authUserJob = launch {
            val localUser = localUserDataSource.login(userLoginForm)
            val serverUser = serverUserDataSource.login(userLoginForm)

            status = if (localUser != null) AuthResult.Success(localUser)
            else if (serverUser != null) AuthResult.Success(serverUser)
            else AuthResult.Error("User not found")
        }

        processJob.join()
        authUserJob.join()

        send(status)

        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun actionUserRegister(userLoginForm: UserLoginForm): Flow<AuthResult> = flow {

    }

}