package com.hizari.data.model.dto.user

import com.hizari.data.model.dto.address.AddressDTO
import com.hizari.data.model.dto.address.GeoLocationDTO

/**
 * Fake Store - com.hizari.domain.model.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class UserDTO(
    val address: AddressDTO? = null,
    val email: String? = null,
    val id: Long? = null,
    val name: NameDTO? = null,
    val phone: String? = null
) {

    companion object {
        fun mock(): UserDTO {
            return  UserDTO(
                address = AddressDTO(
                    city = "kilcoole",
                    street = "7835 new road",
                    number = 3,
                    zipcode = "12926-3874",
                    geoLocation = GeoLocationDTO(lat = "-37.3159", long = "81.1496")
                ),
                email = "John@gmail.com",
                id = 1,
                name = NameDTO(firstname = "John", lastname = "Doe"),
                phone = "1-570-236-7033"
            )
        }
    }

}