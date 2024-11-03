package com.hizari.fakestore.ui.screen.auth.login

import com.hizari.common.data.Result

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

data class LoginViewState(
    val loginResult: Result<Unit> = Result.Empty,
    val password: String = "",
    val passwordError: String = "",
    val username: String = "",
)