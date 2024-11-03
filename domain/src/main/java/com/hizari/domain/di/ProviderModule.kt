package com.hizari.domain.di

import android.content.Context
import com.hizari.domain.provider.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Fake Store - com.hizari.domain.di
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Provides
    @Singleton
    fun provideResourcesProvider(
        @ApplicationContext context: Context
    ): ResourcesProvider {
        return ResourcesProvider(context = context)
    }
}