package com.hizari.domain.repository.cart

import com.hizari.common.data.Result
import com.hizari.domain.model.cart.Cart

/**
 * Fake Store - com.hizari.data.repository.auth
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

interface CartRepository {
    suspend fun getCartCountByUserId(userId: Long): Result<Int>
    suspend fun getCartByUserId(userId: Long): Result<Cart>
}