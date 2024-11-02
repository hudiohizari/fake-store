package com.hizari.fakestore.ui.screen.auth.login

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

sealed interface LoginViewAction {
    data class DoLogin(val doOnSuccess: () -> Unit) : LoginViewAction
}