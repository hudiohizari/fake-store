package com.hizari.fakestore.navigation.main

/**
 * Fake Store - com.hizari.fakestore.navigation.main
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

interface MainNavAction {
    data class GoBack(val result: Map<String, Any?>? = null) : MainNavAction
    data class GoToScreen(val destination: Any, val result: Map<String, Any>? = null) :
        MainNavAction
}