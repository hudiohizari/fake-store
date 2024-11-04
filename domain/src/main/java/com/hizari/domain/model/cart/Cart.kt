package com.hizari.domain.model.cart

/**
 * Fake Store - com.hizari.domain.model.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class Cart(
    val id: Long,
    val products: List<CartProduct>,
) {
    companion object {
        fun mock(id: Long = 0) = Cart(
            id = id,
            products = List(5) { CartProduct.mock(id = it.toLong()) },
        )
    }
}