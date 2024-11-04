package com.hizari.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.hizari.data.local.datastore.token.TokenDataStore
import com.hizari.data.network.interceptor.AccessTokenInterceptor
import com.hizari.data.network.service.AuthService
import com.hizari.data.network.service.UserService
import com.hizari.data.network.util.Client
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
object NetworkModule {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext
        context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context = context).build()
    }

    @Provides
    @Singleton
    fun provideAccessTokenInterceptor(
        tokenDataStore: TokenDataStore
    ): AccessTokenInterceptor {
        return AccessTokenInterceptor(tokenDataStore = tokenDataStore)
    }

    @Provides
    @Singleton
    fun provideClient(
        chuckerInterceptor: ChuckerInterceptor,
        accessTokenInterceptor: AccessTokenInterceptor,
    ): Client {
        return Client(
            chuckerInterceptor = chuckerInterceptor,
            accessTokenInterceptor = accessTokenInterceptor
        )
    }

    @Provides
    @Singleton
    fun provideAuthService(client: Client): AuthService {
        return AuthService.invoke(client = client)
    }

    @Provides
    @Singleton
    fun provideUserService(client: Client): UserService {
        return UserService.invoke(client = client)
    }
}