package com.hizari.domain.di

import com.hizari.domain.repository.cart.CartRepository
import com.hizari.domain.repository.user.UserRepository
import com.hizari.domain.usecase.cart.GetCartCountUseCase
import com.hizari.domain.usecase.cart.GetCartUseCase
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
object CartModule {

    @Provides
    fun provideGetCartCountUseCase(
        cartRepository: CartRepository,
        userRepository: UserRepository,
    ): GetCartCountUseCase {
        return GetCartCountUseCase(
            cartRepository = cartRepository,
            userRepository = userRepository,
        )
    }

    @Provides
    fun provideGetCartUseCase(
        cartRepository: CartRepository,
        userRepository: UserRepository,
    ): GetCartUseCase {
        return GetCartUseCase(
            cartRepository = cartRepository,
            userRepository = userRepository,
        )
    }
}