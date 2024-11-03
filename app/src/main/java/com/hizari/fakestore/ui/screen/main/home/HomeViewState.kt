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
        Product(
            id = it.toLong(),
            title = if (Random.nextBoolean()) "Fjallraven Laptops" else "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops, Fits 15 Laptops, Fits 15 Laptops, Fits 15 Laptops, Fits 15 Laptops",
            price = "$${Random.nextInt(10, 100)}",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        )
    }
)
