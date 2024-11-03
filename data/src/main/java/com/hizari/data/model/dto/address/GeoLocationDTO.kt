package com.hizari.data.model.dto.address

import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.domain.model.address
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data class GeoLocationDTO(
    val lat: String? = null,
    val long: String? = null
)