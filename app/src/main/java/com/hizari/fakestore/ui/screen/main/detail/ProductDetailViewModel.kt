package com.hizari.fakestore.ui.screen.main.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
class ProductDetailViewModel @Inject constructor() : ViewModel() {

    private val mutableViewState = MutableStateFlow(ProductDetailViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (ProductDetailViewState) -> ProductDetailViewState) {
        mutableViewState.update(update)
    }
}