package com.hizari.fakestore.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val mutableViewState = MutableStateFlow(LoginViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (LoginViewState) -> LoginViewState) {
        mutableViewState.update(update)
    }

    fun doAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.DoLogin -> doLogin(action.doOnSuccess)
        }
    }

    private fun doLogin(doOnSuccess: () -> Unit) {
        viewModelScope.launch {
            delay(Random.nextLong(50, 5000))
            doOnSuccess.invoke()
        }
    }

}