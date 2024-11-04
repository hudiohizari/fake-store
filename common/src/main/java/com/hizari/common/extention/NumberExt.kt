package com.hizari.common.extention

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * Fake Store - com.hizari.common.extention
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

fun Long?.orZero(): Long {
    return this ?: 0
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Long?.toDotFormat(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    val formatter = DecimalFormat("##,###,###,###,###", symbols)
    return formatter.format(this.orZero())
}

fun Double?.toDotFormat(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    val formatter = DecimalFormat("##,###,###,##0.##", symbols)
    return formatter.format(this ?: 0.0)
}
