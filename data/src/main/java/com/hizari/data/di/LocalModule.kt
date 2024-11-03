package com.hizari.data.di

import android.content.Context
import com.hizari.data.local.datastore.token.TokenDataStore
import com.hizari.data.local.datastore.token.TokenDataStoreImpl
import com.hizari.data.local.datastore.user.UserDataStore
import com.hizari.data.local.datastore.user.UserDataStoreImpl
import com.hizari.domain.provider.ResourcesProvider
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
object LocalModule {

    @Provides
    @Singleton
    fun provideUserDataStore(
        @ApplicationContext context: Context,
        resourcesProvider: ResourcesProvider
    ): UserDataStore {
        return UserDataStoreImpl(
            context = context,
            resourcesProvider = resourcesProvider
        )
    }

    @Provides
    @Singleton
    fun provideTokenDataStore(
        @ApplicationContext context: Context,
        resourcesProvider: ResourcesProvider
    ): TokenDataStore {
        return TokenDataStoreImpl(
            context = context,
            resourcesProvider = resourcesProvider
        )
    }

}