package com.hizari.domain.repository.auth

import com.hizari.common.data.Result
import com.hizari.domain.model.auth.Token

/**
 * Fake Store - com.hizari.data.repository.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

interface AuthRepository {
    suspend fun postLogin(username: String, password: String): Result<Token>
    suspend fun setAccessToken(token: String): Result<Unit>
    suspend fun getAccessToken(): Result<Token>
    suspend fun clearAccessToken(): Result<Unit>
}