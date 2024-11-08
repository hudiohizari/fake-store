package com.hizari.common.extention

/**
 * Fake Store - com.hizari.common.extention
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

fun String?.isNotNullAndEmpty(): Boolean {
    return isNullOrEmpty().not()
}

fun String.capitalizeWord(): String {
    return this.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
}