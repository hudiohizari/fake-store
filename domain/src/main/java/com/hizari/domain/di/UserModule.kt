package com.hizari.domain.di

import com.hizari.domain.repository.user.UserRepository
import com.hizari.domain.usecase.user.ObserveLoggedInUserUseCase
import com.hizari.domain.usecase.user.RetrieveLoggedInUserUseCase
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
object UserModule {

    @Provides
    fun provideObserveLoggedInUserUseCase(
        userRepository: UserRepository,
    ): ObserveLoggedInUserUseCase {
        return ObserveLoggedInUserUseCase(
            userRepository = userRepository,
        )
    }

    @Provides
    fun provideRetrieveLoggedInUserUseCase(
        userRepository: UserRepository,
    ): RetrieveLoggedInUserUseCase {
        return RetrieveLoggedInUserUseCase(
            userRepository = userRepository,
        )
    }
}