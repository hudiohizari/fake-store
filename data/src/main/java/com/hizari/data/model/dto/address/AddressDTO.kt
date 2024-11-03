package com.hizari.data.model.dto.address

import com.google.gson.annotations.SerializedName

/**
 * Fake Store - com.hizari.domain.model.address
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class AddressDTO(
    val city: String? = null,
    @SerializedName("geolocation")
    val geoLocation: GeoLocationDTO? = null,
    val number: Int? = null,
    val street: String? = null,
    val zipcode: String? = null,
)