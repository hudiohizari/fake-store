package com.hizari.fakestore.ui.screen.main.profile

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

sealed interface ProfileViewAction {
    data object LoadUser : ProfileViewAction
    data object Logout : ProfileViewAction
}