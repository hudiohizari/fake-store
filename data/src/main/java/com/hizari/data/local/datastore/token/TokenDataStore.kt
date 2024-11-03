package com.hizari.data.local.datastore.token

import com.hizari.common.data.Result
import com.hizari.data.model.dto.auth.TokenDTO

/**
 * Fake Store - com.hizari.data.local.datastore.token
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

interface TokenDataStore {
    suspend fun setAccessToken(dto: TokenDTO): Result<TokenDTO>
    suspend fun getAccessToken(): Result<TokenDTO>
    suspend fun clearAccessToken(): Result<Boolean>
}