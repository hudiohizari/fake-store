package com.hizari.fakestore.ui.screen.main.cart

import com.hizari.common.extention.toDotFormat
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */
data class CartViewState(
    val cartList: List<Cart> = listOf(
        Cart(
            id = 1,
            product = Product.mock(),
            quantity = 1,
        ),
        Cart(
            id = 2,
            product = Product.mock(id = 2),
            quantity = 1,
        ),
        Cart(
            id = 3,
            product = Product.mock(id = 3),
            quantity = 1,
        )
    ),
) {

    fun getTotalPrice(): String {
        return "Rp${
            cartList.sumOf { cart ->
                cart.product.price.filter { it.isDigit() }.toLong() * cart.quantity
            }.toDotFormat()
        }"
    }

}
