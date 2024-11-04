package com.hizari.domain.model.cart

import com.hizari.common.extention.toDotFormat

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
        fun empty(): Cart = Cart(
            id = -1,
            products = emptyList(),
        )

        fun mock(id: Long = 0) = Cart(
            id = id,
            products = List(5) { CartProduct.mock(id = it.toLong()) },
        )
    }

    fun getTotalPrice(): String {
        return "$${
            products.sumOf { cart ->
                cart.product.price.replace("[^\\d.]".toRegex(), "").toDouble() * cart.quantity
            }.toDotFormat()
        }"
    }


}