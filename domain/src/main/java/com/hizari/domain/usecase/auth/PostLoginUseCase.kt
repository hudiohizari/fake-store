package com.hizari.domain.usecase.auth

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.domain.model.auth.Token
import com.hizari.domain.repository.auth.AuthRepository
import com.hizari.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Fake Store - com.hizari.domain.usecase.auth
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

class PostLoginUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        username: String,
        password: String
    ): Flow<Result<Unit>> = flow {
        emit(Result.Loading)

        val loginResult = authRepository.postLogin(username, password)
        val result = processLoginResult(loginResult)

        emit(result)
    }

    private suspend fun processLoginResult(loginResult: Result<Token>): Result<Unit> {
        return handleResult(
            resultCall = { loginResult },
            onSuccess = { token ->
                saveTokenAndUser(token)
            },
            onError = { code, message, throwable ->
                Result.Error(code, message, throwable)
            }
        )
    }

    private suspend fun saveTokenAndUser(token: Token): Result<Unit> {
        val userResult = userRepository.getCurrentUser()

        return handleResult(
            resultCall = { userResult },
            onSuccess = { user ->
                val saveUserResult = userRepository.saveLoggedInUser(user)
                val saveTokenResult = authRepository.saveAccessToken(token.token)

                if (saveUserResult.success() && saveTokenResult.error()) {
                    Result.Success(Unit)
                } else {
                    resolveSaveError(saveUserResult, saveTokenResult)
                }
            }
        )
    }

    private fun resolveSaveError(
        saveUserResult: Result<*>,
        saveTokenResult: Result<*>
    ): Result<Unit> {
        return when {
            saveUserResult is Result.Error -> Result.Error(message = saveUserResult.asMessage())
            saveTokenResult is Result.Error -> Result.Error(message = saveTokenResult.asMessage())
            else -> Result.Error(message = "Unknown error occurred during login.")
        }
    }
}