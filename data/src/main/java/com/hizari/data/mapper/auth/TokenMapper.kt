package com.hizari.data.mapper.auth

import com.hizari.data.model.dto.auth.TokenDTO
import com.hizari.domain.model.auth.Token

/**
 * Fake Store - com.hizari.data.mapper.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

fun TokenDTO.toDomain(): Token {
    return Token(
        token = token.orEmpty(),
    )
}