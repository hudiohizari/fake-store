package com.hizari.domain.model.product

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
)