package com.hizari.data.repository.product

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.data.mapper.product.toDomain
import com.hizari.data.mapper.user.toDTO
import com.hizari.data.network.service.ProductService
import com.hizari.data.network.util.SafeApiRequest
import com.hizari.domain.model.product.Product
import com.hizari.domain.repository.product.ProductRepository
import javax.inject.Inject

/**
 * Fake Store - com.hizari.data.repository.product
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
) : ProductRepository, SafeApiRequest() {

    override suspend fun getProductCategoryList(): Result<List<String>> {
        return handleResult(
            resultCall = {
                apiRequest {
                    productService.getProductCategoryList()
                }
            },
            onSuccess = { response ->
                Result.Success(response)
            }
        )
    }

    override suspend fun getProductList(): Result<List<Product>> {
        return handleResult(
            resultCall = {
                apiRequest {
                    productService.getProductList()
                }
            },
            onSuccess = { response ->
                Result.Success(response.map { it.toDomain() })
            }
        )
    }

    override suspend fun getProductById(id: Long): Result<Product> {
        return handleResult(
            resultCall = {
                apiRequest {
                    productService.getProductById(id)
                }
            },
            onSuccess = { response ->
                Result.Success(response.toDomain())
            }
        )
    }

    override suspend fun getProductListByCategory(category: String): Result<List<Product>> {
        return handleResult(
            resultCall = {
                apiRequest {
                    productService.getProductListByCategory(category)
                }
            },
            onSuccess = { response ->
                Result.Success(response.map { it.toDomain() })
            }
        )
    }

}