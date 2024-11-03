package com.hizari.domain.repository.auth

import com.hizari.common.data.Result

/**
 * Fake Store - com.hizari.data.repository.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

interface AuthRepository {
    suspend fun postLogin(username: String, password: String): Result<Nothing>
    suspend fun saveAccessToken(token: String): Result<Nothing>
}