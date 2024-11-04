package com.hizari.fakestore.ui.screen.main.cart

import com.hizari.common.extention.toDotFormat
import com.hizari.domain.model.cart.Cart

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */
data class CartViewState(
    val cart: Cart = Cart.mock()
) {

    fun getTotalPrice(): String {
        return "Rp${
            cart.products.sumOf { cart ->
                cart.product.price.filter { it.isDigit() }.toLong() * cart.quantity
            }.toDotFormat()
        }"
    }

}
