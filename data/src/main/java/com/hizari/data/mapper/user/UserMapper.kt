package com.hizari.data.mapper.user

import com.hizari.common.extention.orZero
import com.hizari.data.mapper.address.toDomain
import com.hizari.data.model.dto.user.UserDTO
import com.hizari.domain.model.address.Address
import com.hizari.domain.model.user.Name
import com.hizari.domain.model.user.User

/**
 * Fake Store - com.hizari.data.mapper.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

fun UserDTO.toDomain(): User {
    return User(
        address = address?.toDomain() ?: Address.empty(),
        id = id.orZero(),
        email = email.orEmpty(),
        name = name?.toDomain() ?: Name.empty(),
        phone = phone.orEmpty(),
    )
}