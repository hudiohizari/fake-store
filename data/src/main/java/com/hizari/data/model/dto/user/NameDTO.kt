package com.hizari.data.model.dto.user

import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.domain.model.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data class NameDTO(
    val firstname: String,
    val lastname: String
)