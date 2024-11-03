package com.hizari.data.local.datastore.user

import com.hizari.common.data.Result
import com.hizari.data.model.dto.user.UserDTO
import kotlinx.coroutines.flow.Flow

/**
 * Fake Store - com.hizari.data.local.datastore.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

interface UserDataStore {
    suspend fun setUserData(dto: UserDTO): Result<UserDTO>
    suspend fun getUserData(): Result<UserDTO>
    suspend fun clearUserData(): Result<Boolean>

    fun observeUserData(): Flow<Result<UserDTO>>
}