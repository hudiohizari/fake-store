package com.hizari.fakestore.ui.screen.main.home

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

sealed interface HomeViewAction {
    data object LoadCartCount : HomeViewAction
    data object LoadCategoryList : HomeViewAction
    data class LoadProductList(val category: String?) : HomeViewAction
}