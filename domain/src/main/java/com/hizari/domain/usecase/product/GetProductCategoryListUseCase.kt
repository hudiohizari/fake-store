package com.hizari.domain.usecase.product

import com.hizari.common.data.Result
import com.hizari.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * SPPI - com.hizari.domain.usecase.product
 *
 * Created by hudiohizari on 26/09/24.
 * https://github.com/hudiohizari
 *
 */

class GetProductCategoryListUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(): Flow<Result<List<String>>> = flow {
        emit(Result.Loading)
        emit(productRepository.getProductCategoryList())
    }
}