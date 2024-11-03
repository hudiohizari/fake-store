package com.hizari.domain.model.user

/**
 * Fake Store - com.hizari.domain.model.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class Name(
    val firstname: String,
    val lastname: String
) {
    companion object {
        fun empty(): Name {
            return Name(firstname = "", lastname = "")
        }
    }
}