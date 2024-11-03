package com.hizari.data.repository.auth

import com.hizari.common.data.Result
import com.hizari.data.local.datastore.token.TokenDataStore
import com.hizari.data.mapper.auth.toDomain
import com.hizari.data.model.dto.auth.TokenDTO
import com.hizari.data.model.request.auth.PostLoginRequest
import com.hizari.data.network.service.AuthService
import com.hizari.data.network.util.SafeApiRequest
import com.hizari.domain.model.auth.Token
import com.hizari.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * Fake Store - com.hizari.data.repository.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenDataStore: TokenDataStore,
) : AuthRepository, SafeApiRequest() {

    override suspend fun postLogin(username: String, password: String): Result<Token> {
        return handleResult(
            resultCall = {
                apiRequest {
                    val body = PostLoginRequest(
                        username = username,
                        password = password
                    )
                    authService.postLogin(body = body)
                }
            },
            onSuccess = {
                Result.Success(it.toDomain())
            }
        )
    }

    override suspend fun saveAccessToken(token: String): Result<Unit> {
        return handleResult(
            resultCall = {
                tokenDataStore.setAccessToken(TokenDTO((token)))
            },
            onSuccess = {
                Result.Success(Unit)
            }
        )
    }

}