package com.hizari.data.mapper.address

import com.hizari.common.extention.orZero
import com.hizari.data.model.dto.address.AddressDTO
import com.hizari.domain.model.address.Address
import com.hizari.domain.model.address.GeoLocation

/**
 * Fake Store - com.hizari.data.mapper.address
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

fun AddressDTO.toDomain(): Address {
    return Address(
        city = city.orEmpty(),
        geoLocation = geoLocation?.toDomain() ?: GeoLocation.empty(),
        number = number.orZero(),
        street = street.orEmpty(),
        zipcode = zipcode.orEmpty(),
    )
}