package com.hizari.data.repository.cart

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.common.extention.orZero
import com.hizari.data.mapper.cart.toDomain
import com.hizari.data.mapper.product.toDomain
import com.hizari.data.network.service.CartService
import com.hizari.data.network.service.ProductService
import com.hizari.data.network.util.SafeApiRequest
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.cart.CartProduct
import com.hizari.domain.repository.cart.CartRepository
import javax.inject.Inject

/**
 * Fake Store - com.hizari.data.repository.product
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

class CartRepositoryImpl @Inject constructor(
    private val cartService: CartService,
    private val productService: ProductService,
) : CartRepository, SafeApiRequest() {

    override suspend fun getCartCountByUserId(userId: Long): Result<Int> {
        return handleResult(
            resultCall = {
                apiRequest {
                    cartService.getCartByUserId(userId = userId)
                }
            },
            onSuccess = { response ->
                Result.Success(response.last().products?.size.orZero())
            }
        )
    }

    override suspend fun getCartByUserId(userId: Long): Result<Cart> {
        return handleResult(
            resultCall = {
                apiRequest {
                    cartService.getCartByUserId(userId = userId)
                }
            },
            onSuccess = { response ->
                // Getting last cart
                val products = mutableListOf<CartProduct>()
                response.last().products?.forEach {
                    val productRes = apiRequest {
                        productService.getProductById(id = it.productId.orZero())
                    }
                    if (productRes is Result.Success) {
                        products.add(
                            CartProduct(
                                product = productRes.data.toDomain(),
                                quantity = it.quantity.orZero()
                            )
                        )
                    }
                }
                Result.Success(response.last().toDomain(products))
            }
        )
    }

}