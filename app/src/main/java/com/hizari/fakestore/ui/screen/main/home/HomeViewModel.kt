package com.hizari.fakestore.ui.screen.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizari.domain.usecase.cart.GetCartCountUseCase
import com.hizari.domain.usecase.product.GetProductCategoryListUseCase
import com.hizari.domain.usecase.product.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.home
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCartCountUseCase: GetCartCountUseCase,
    private val getProductCategoryListUseCase: GetProductCategoryListUseCase,
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(HomeViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (HomeViewState) -> HomeViewState) {
        mutableViewState.update(update)
    }

    fun doAction(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.LoadCartCount -> loadCartCount()
            is HomeViewAction.LoadCategoryList -> loadCategoryList()
            is HomeViewAction.LoadProductList -> loadProductList(action.category)
        }
    }

    private fun loadCartCount() {
        getCartCountUseCase.invoke().onEach { res ->
            updateViewState {
                it.copy(cartCountResult = res)
            }
        }.launchIn(viewModelScope)
    }

    private fun loadCategoryList() {
        getProductCategoryListUseCase.invoke().onEach { res ->
            updateViewState {
                it.copy(categoryListResult = res)
            }
        }.launchIn(viewModelScope)
    }

    private fun loadProductList(category: String?) {
        getProductListUseCase.invoke(category).onEach { res ->
            updateViewState {
                it.copy(productListResult = res)
            }
        }.launchIn(viewModelScope)
    }
}