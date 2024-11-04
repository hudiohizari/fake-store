package com.hizari.domain.model.cart

import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.domain.model.cart
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

data class CartProduct(
    val product: Product,
    val quantity: Int
) {
    companion object {
        fun mock(id: Long = 0) = CartProduct(
            product = Product.mock(id = id),
            quantity = 1
        )
    }
}
