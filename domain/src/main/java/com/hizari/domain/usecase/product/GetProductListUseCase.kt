package com.hizari.domain.usecase.product

import com.hizari.common.data.Result
import com.hizari.common.extention.isNotNullAndEmpty
import com.hizari.domain.model.product.Product
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

class GetProductListUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(category: String?): Flow<Result<List<Product>>> = flow {
        emit(Result.Loading)
        if (category.isNullOrEmpty().not()) {
            emit(productRepository.getProductListByCategory(category?.lowercase().orEmpty()))
        } else {
            emit(productRepository.getProductList())
        }
    }
}