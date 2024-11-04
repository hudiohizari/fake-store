package com.hizari.fakestore.ui.screen.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizari.domain.usecase.cart.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {
    private val mutableViewState = MutableStateFlow(CartViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (CartViewState) -> CartViewState) {
        mutableViewState.update(update)
    }

    fun doAction(action: CartViewAction) {
        when (action) {
            is CartViewAction.LoadCart -> loadCart()
        }
    }

    private fun loadCart() {
        getCartUseCase.invoke().onEach { res ->
            updateViewState {
                it.copy(cartResult = res)
            }
        }.launchIn(viewModelScope)
    }
}