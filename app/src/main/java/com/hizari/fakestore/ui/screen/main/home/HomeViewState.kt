package com.hizari.fakestore.ui.screen.main.home

import com.hizari.domain.model.product.Product
import kotlin.random.Random

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.home
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class HomeViewState(
    val cartCounts: Int = Random.nextInt(10),
    val categoryList: List<String> = listOf(
        "All",
        "Electronics",
        "Jewelery",
        "Men's Clothing",
        "Women's Clothing"
    ),
    val selectedCategory: String = categoryList.first(),
    val productList: List<Product> = List(10) {
        Product.mock(id = it.toLong())
    }
)
