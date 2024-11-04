package com.hizari.data.di

import com.hizari.data.repository.auth.AuthRepositoryImpl
import com.hizari.data.repository.cart.CartRepositoryImpl
import com.hizari.data.repository.product.ProductRepositoryImpl
import com.hizari.data.repository.user.UserRepositoryImpl
import com.hizari.domain.repository.auth.AuthRepository
import com.hizari.domain.repository.cart.CartRepository
import com.hizari.domain.repository.product.ProductRepository
import com.hizari.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Fake Store - com.hizari.data.di
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBind {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository
}