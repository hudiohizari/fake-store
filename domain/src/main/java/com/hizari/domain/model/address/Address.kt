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
    val geoLocation: GeoLocation,
    val number: Int,
    val street: String,
    val zipcode: String,
) {
    companion object {
        fun empty(): Address {
            return Address(
                city = "",
                geoLocation = GeoLocation.empty(),
                number = 0,
                street = "",
                zipcode = ""
            )
        }
    }
}