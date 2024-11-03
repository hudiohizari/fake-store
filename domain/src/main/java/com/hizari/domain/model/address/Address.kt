package com.hizari.domain.model.address

/**
 * Fake Store - com.hizari.domain.model.address
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class Address(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val geoLocation: GeoLocation
)