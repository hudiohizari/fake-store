package com.hizari.fakestore.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizari.common.data.Result
import com.hizari.domain.model.user.User
import com.hizari.domain.usecase.user.ObserveLoggedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Fake Store - com.hizari.fakestore.ui.activity
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    observeLoggedInUserUseCase: ObserveLoggedInUserUseCase
): ViewModel() {

    val userResult: StateFlow<Result<User>> = observeLoggedInUserUseCase.invoke()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = Result.Loading
        )
}