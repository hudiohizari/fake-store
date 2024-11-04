package com.hizari.data.repository.cart

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.common.extention.orZero
import com.hizari.data.mapper.cart.toDomain
import com.hizari.data.mapper.product.toDomain
import com.hizari.data.mapper.user.toDTO
import com.hizari.data.network.service.CartService
import com.hizari.data.network.service.ProductService
import com.hizari.data.network.util.SafeApiRequest
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.cart.CartProduct
import com.hizari.domain.model.product.Product
import com.hizari.domain.repository.cart.CartRepository
import com.hizari.domain.repository.product.ProductRepository
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

    override suspend fun getCartByUserId(userId: Long): Result<Cart> {
        return handleResult(
            resultCall = {
                apiRequest {
                    cartService.getCartByUserId(userId = userId)
                }
            },
            onSuccess = { response ->
                val products = mutableListOf<CartProduct>()
                response.products?.forEach {
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
                Result.Success(response.toDomain(products))
            }
        )
    }

}