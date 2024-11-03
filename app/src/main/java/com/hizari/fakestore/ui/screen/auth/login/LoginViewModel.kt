package com.hizari.fakestore.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.domain.usecase.auth.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(LoginViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (LoginViewState) -> LoginViewState) {
        mutableViewState.update(update)
    }

    fun doAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.DoLogin -> doLogin()
        }
    }

    private fun doLogin() {
        postLoginUseCase.invoke(
            username = viewState.value.username,
            password = viewState.value.password,
        ).onEach { res ->
            when (res) {
                is Result.Error -> {
                    updateViewState {
                        it.copy(
                            loginResult = res,
                            passwordError = res.asMessage()
                        )
                    }
                }
                else -> {
                    updateViewState {
                        it.copy(loginResult = res)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}