package com.hizari.data.mapper.user

import com.hizari.data.model.dto.user.NameDTO
import com.hizari.domain.model.user.Name

/**
 * Fake Store - com.hizari.data.mapper.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

fun NameDTO.toDomain(): Name {
    return Name(
        firstname = firstname,
        lastname = lastname
    )
}