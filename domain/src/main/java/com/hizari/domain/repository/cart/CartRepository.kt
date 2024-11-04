package com.hizari.domain.repository.cart

import com.hizari.common.data.Result
import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.data.repository.auth
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

interface CartRepository {
    suspend fun getCartByUserId(userId: Long): Result<List<Product>>
}