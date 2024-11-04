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
    suspend fun getUserById(userId: Long): Result<User>
    suspend fun setLoggedInUser(user: User): Result<Unit>
    suspend fun getLoggedInUser(): Result<User>
    suspend fun clearLoggedInUser(): Result<Unit>

    /**
     * Observe function
     * */
    fun observeLoggedInUser(): Flow<Result<User>>
}