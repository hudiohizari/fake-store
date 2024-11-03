package com.hizari.fakestore.ui.screen.main.profile

import com.hizari.domain.model.user.User

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.profile
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class ProfileViewState(
    val user: User = User.mock()
)