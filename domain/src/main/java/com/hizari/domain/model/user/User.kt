package com.hizari.domain.model.user

import com.hizari.domain.model.address.Address
import com.hizari.domain.model.address.GeoLocation

/**
 * Fake Store - com.hizari.domain.model.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class User(
    val address: Address,
    val email: String,
    val id: Long,
    val name: Name,
    val phone: String
) {

    companion object {
        fun mock(id: Long = 1): User {
            return  User(
                id = id,
                email = "John@gmail.com",
                name = Name(firstname = "John", lastname = "Doe"),
                address = Address(
                    city = "kilcoole",
                    street = "7835 new road",
                    number = 3,
                    zipcode = "12926-3874",
                    geoLocation = GeoLocation(lat = "-37.3159", long = "81.1496")
                ),
                phone = "1-570-236-7033"
            )
        }
    }

}