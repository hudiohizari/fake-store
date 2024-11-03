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
    suspend fun getCurrentUser(): Result<User>
    suspend fun retrieveLoggedInUser(): Result<User>
    suspend fun saveLoggedInUser(user: User): Result<Unit>

    /**
     * Observe function
     * */
    suspend fun observeLoggedInUser(): Flow<Result<User>>
}