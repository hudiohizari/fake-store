package com.hizari.data.mapper.product

import com.hizari.common.extention.orZero
import com.hizari.common.extention.toDotFormat
import com.hizari.data.model.dto.product.ProductDTO
import com.hizari.domain.model.product.Product

/**
 * Fake Store - com.hizari.data.mapper.product
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

fun ProductDTO.toDomain(): Product {
    return Product(
        category = category.orEmpty(),
        description = description.orEmpty(),
        id = id.orZero(),
        image = image.orEmpty(),
        price = "$${price.toDotFormat()}",
        rating = "${rating?.rate.orZero()} / 5 (${rating?.count.orZero()})",
        title = title.orEmpty(),
    )
}