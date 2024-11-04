package com.hizari.fakestore.ui.screen.main.home

import com.hizari.common.data.Result
import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.home
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class HomeViewState(
    val cartCountResult: Result<Int> = Result.Empty,
    val categoryListResult: Result<List<String>> = Result.Empty,
    val selectedCategory: String? = null,
    val showProfile: Boolean = false,
    val showAddedToCart: Boolean = false,
    val productAddedToCart: Product? = null,
    val productListResult: Result<List<Product>> = Result.Empty
)
