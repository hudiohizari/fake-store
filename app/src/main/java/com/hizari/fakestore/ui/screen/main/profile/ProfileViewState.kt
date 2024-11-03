package com.hizari.fakestore.ui.screen.main.profile

import com.hizari.common.data.Result
import com.hizari.domain.model.user.User

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.profile
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class ProfileViewState(
    val logoutResult: Result<Unit> = Result.Empty,
    val userResult: Result<User> = Result.Empty,
)