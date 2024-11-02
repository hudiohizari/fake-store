package com.hizari.common.extention

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Fake Store - com.hizari.common.extention
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

fun Context.toast(@StringRes id: Int, isLong: Boolean = false) {
    toast(getString(id), isLong)
}

fun Context.toast(message: String?, isLong: Boolean = false) {
    Toast.makeText(
        this,
        message.orEmpty(),
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}