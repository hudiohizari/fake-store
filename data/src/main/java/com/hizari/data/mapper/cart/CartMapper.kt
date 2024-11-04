package com.hizari.data.mapper.cart

import com.hizari.common.extention.orZero
import com.hizari.data.model.dto.cart.CartDTO
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.cart.CartProduct

/**
 * Fake Store - com.hizari.data.mapper.cart
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

fun CartDTO.toDomain(products: List<CartProduct>): Cart {
    return Cart(
        id = id.orZero(),
        products = products,
    )
}