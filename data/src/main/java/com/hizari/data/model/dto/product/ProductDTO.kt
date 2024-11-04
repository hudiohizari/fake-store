package com.hizari.data.model.dto.product

/**
 * Fake Store - com.hizari.data.model.dto.product
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

data class ProductDTO(
    val category: String? = null,
    val description: String? = null,
    val id: Long? = null,
    val image: String? = null,
    val price: Double? = null,
    val rating: RatingDTO? = null,
    val title: String? = null
)