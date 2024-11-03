package com.hizari.domain.di

import com.hizari.domain.repository.auth.AuthRepository
import com.hizari.domain.repository.user.UserRepository
import com.hizari.domain.usecase.auth.PostLoginUseCase
import com.hizari.domain.usecase.user.ObserveLoggedInUserUseCase
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
    fun providePostLoginUseCase(
        authRepository: AuthRepository,
        userRepository: UserRepository,
    ): PostLoginUseCase {
        return PostLoginUseCase(
            authRepository = authRepository,
            userRepository = userRepository
        )
    }
}