package com.hizari.fakestore.ui.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizari.domain.usecase.auth.LogoutUseCase
import com.hizari.domain.usecase.user.GetLoggedInUserUseCase
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
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ProfileViewState())
    val viewState = mutableViewState.asStateFlow()

    fun updateViewState(update: (ProfileViewState) -> ProfileViewState) {
        mutableViewState.update(update)
    }

    fun doAction(action: ProfileViewAction) {
        when (action) {
            is ProfileViewAction.LoadUser -> loadUser()
            is ProfileViewAction.Logout -> doLogout()
        }
    }

    private fun loadUser() {
        getLoggedInUserUseCase.invoke().onEach { res ->
            updateViewState {
                it.copy(userResult = res)
            }
        }.launchIn(viewModelScope)
    }

    private fun doLogout() {
        logoutUseCase.invoke().onEach { res ->
            updateViewState {
                it.copy(logoutResult = res)
            }
        }.launchIn(viewModelScope)
    }
}