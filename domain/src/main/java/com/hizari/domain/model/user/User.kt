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
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val address: Address,
    val phone: String
) {

    companion object {
        fun mock(): User {
            return  User(
                id = 1,
                email = "John@gmail.com",
                username = "johnd",
                password = "m38rmF$",
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