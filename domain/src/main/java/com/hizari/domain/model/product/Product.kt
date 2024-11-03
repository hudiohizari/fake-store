package com.hizari.domain.model.product

import com.hizari.common.extention.toDotFormat
import kotlin.random.Random

/**
 * Fake Store - com.hizari.domain.model.product
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class Product(
    val category: String,
    val description: String,
    val id: Long,
    val image: String,
    val price: String,
    val title: String
) {
    companion object {
        private fun randomTitle(): String {
            return when (Random.nextInt(3)) {
                0 -> "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
                1 -> "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops, So I Don't Have To Carry Everything"
                2 -> "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops, Can Carry A Suitcase Too"
                else -> "Fjallraven - Foldsack"
            }
        }

        fun mock(id: Long = 1) = Product(
            id = id,
            title = "$id ${randomTitle()}",
            price = "Rp${Random.nextLong(1000, 10000000).toDotFormat()}",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        )
    }
}