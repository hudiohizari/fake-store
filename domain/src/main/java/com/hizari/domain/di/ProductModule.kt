package com.hizari.domain.di

import com.hizari.domain.repository.product.ProductRepository
import com.hizari.domain.usecase.product.GetProductCategoryListUseCase
import com.hizari.domain.usecase.product.GetProductListUseCase
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
object ProductModule {

    @Provides
    fun provideGetProductCategoryListUseCase(
        productRepository: ProductRepository,
    ): GetProductCategoryListUseCase {
        return GetProductCategoryListUseCase(
            productRepository = productRepository,
        )
    }

    @Provides
    fun provideGetProductListUseCase(
        productRepository: ProductRepository,
    ): GetProductListUseCase {
        return GetProductListUseCase(
            productRepository = productRepository,
        )
    }
}