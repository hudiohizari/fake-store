package com.hizari.fakestore.ui.screen.main.detail

import com.hizari.domain.model.product.Product
import kotlin.random.Random

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.home
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class ProductDetailViewState(
    val addedQuantity: Int = 1,
    val product: Product = Product.mock(),
    val quantity: Int = 1,
    val showAddToCart: Boolean = false,
)
