package com.hizari.domain.provider

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Fake Store - com.hizari.domain.provider
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

class ResourcesProvider(@ApplicationContext private val context: Context) {

    fun getContext(): Context = context

    fun getString(
        @StringRes id: Int,
        vararg formatArgs: Any
    ): String {
        return context.getString(id, *formatArgs)
    }

}