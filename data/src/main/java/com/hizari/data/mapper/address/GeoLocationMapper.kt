package com.hizari.data.mapper.address

import com.hizari.data.model.dto.address.GeoLocationDTO
import com.hizari.domain.model.address.GeoLocation

/**
 * Fake Store - com.hizari.data.mapper.address
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

fun GeoLocationDTO.toDomain(): GeoLocation {
    return GeoLocation(
        lat = lat.orEmpty(),
        long = long.orEmpty()
    )
}

fun GeoLocation.toDTO(): GeoLocationDTO {
    return GeoLocationDTO(
        lat = lat,
        long = long
    )
}