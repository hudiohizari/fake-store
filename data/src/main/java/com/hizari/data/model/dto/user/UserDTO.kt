package com.hizari.data.model.dto.user

import com.hizari.data.model.dto.address.AddressDTO

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
)