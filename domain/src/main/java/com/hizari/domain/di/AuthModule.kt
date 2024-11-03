package com.hizari.domain.di

import com.hizari.domain.provider.ResourcesProvider
import com.hizari.domain.repository.auth.AuthRepository
import com.hizari.domain.repository.user.UserRepository
import com.hizari.domain.usecase.auth.LoginUseCase
import com.hizari.domain.usecase.auth.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Fake Store - com.hizari.domain.di
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository,
        resourcesProvider: ResourcesProvider,
        userRepository: UserRepository,
    ): LoginUseCase {
        return LoginUseCase(
            authRepository = authRepository,
            resourcesProvider = resourcesProvider,
            userRepository = userRepository
        )
    }

    @Provides
    fun provideLogoutUseCase(
        authRepository: AuthRepository,
        resourcesProvider: ResourcesProvider,
        userRepository: UserRepository,
    ): LogoutUseCase {
        return LogoutUseCase(
            authRepository = authRepository,
            resourcesProvider = resourcesProvider,
            userRepository = userRepository
        )
    }
}