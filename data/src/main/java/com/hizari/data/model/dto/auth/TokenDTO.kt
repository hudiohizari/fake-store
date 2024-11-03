package com.hizari.data.model.dto.auth

import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.data.model.dto.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data class TokenDTO(
    val token: String?
)
