package com.hizari.fakestore.ui.screen.main.cart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    private val mutableViewState = MutableStateFlow(CartViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (CartViewState) -> CartViewState) {
        mutableViewState.update(update)
    }
}