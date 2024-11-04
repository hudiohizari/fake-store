package com.hizari.fakestore.ui.screen.main.cart

import com.hizari.common.data.Result
import com.hizari.domain.model.cart.Cart

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */
data class CartViewState(
    val cartResult: Result<Cart> = Result.Empty
)
