package com.hizari.domain.model.cart

import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.domain.model.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class Cart(
    val id: Long,
    val product: Product,
    val quantity: Int
)