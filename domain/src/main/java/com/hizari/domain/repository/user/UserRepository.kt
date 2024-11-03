package com.hizari.domain.repository.user

import com.hizari.common.data.Result
import com.hizari.domain.model.user.User
import kotlinx.coroutines.flow.Flow

/**
 * Fake Store - com.hizari.domain.repository.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */
interface UserRepository {
    fun getLoggedInUser(): Result<User>
    fun saveLoggedInUser(user: User): Result<Nothing>
    fun observeLoggedInUser(): Flow<Result<User>>
}