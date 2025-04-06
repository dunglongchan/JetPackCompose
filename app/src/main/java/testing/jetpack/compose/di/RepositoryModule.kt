package testing.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import testing.jetpack.compose.data.LocalUserDataSource
import testing.jetpack.compose.data.ServerUserDataSource
import testing.jetpack.compose.data.repo.UserImpl
import testing.jetpack.compose.domain.repository.UserRepository


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        localUserDataSource: LocalUserDataSource,
        serverUserDataSource: ServerUserDataSource
    ): UserRepository {
        return UserImpl(localUserDataSource, serverUserDataSource)
    }

    @Provides
    fun provideLocalUserDataSource(): LocalUserDataSource {
        return LocalUserDataSource()
    }

    @Provides
    fun provideServerUserDataSource(): ServerUserDataSource {
        return ServerUserDataSource()
    }
}