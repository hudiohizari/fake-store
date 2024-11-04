package com.hizari.domain.repository.product

import com.hizari.common.data.Result
import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.data.repository.auth
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

interface ProductRepository {
    suspend fun getProductCategoryList(): Result<List<String>>
    suspend fun getProductList(): Result<List<Product>>
    suspend fun getProductById(id: Long): Result<Product>
    suspend fun getProductListByCategory(category: String): Result<List<Product>>
}