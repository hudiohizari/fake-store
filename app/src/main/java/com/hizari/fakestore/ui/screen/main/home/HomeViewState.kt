package com.hizari.fakestore.ui.screen.main.home

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
    val categories: List<String> = listOf(
        "All",
        "Electronics",
        "Jewelery",
        "Men's Clothing",
        "Women's Clothing"
    ),
    val selectedCategory: String = categories.first(),
)
