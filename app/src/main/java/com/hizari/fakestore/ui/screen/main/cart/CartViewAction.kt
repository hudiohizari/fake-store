package com.hizari.fakestore.ui.screen.main.cart

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

sealed interface CartViewAction {
    data object LoadCart : CartViewAction
}