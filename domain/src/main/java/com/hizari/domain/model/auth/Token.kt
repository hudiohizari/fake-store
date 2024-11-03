package com.hizari.domain.model.auth

/**
 * Fake Store - com.hizari.domain.model.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class Token(
    val token: String,
) {
    companion object {
        fun empty() = Token(
            token = ""
        )
    }
}
