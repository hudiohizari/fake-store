package com.hizari.domain.usecase.auth

import com.hizari.common.data.Result
import com.hizari.domain.R
import com.hizari.domain.provider.ResourcesProvider
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

class LogoutUseCase(
    private val authRepository: AuthRepository,
    private val resourcesProvider: ResourcesProvider,
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Result<Unit>> = flow {
        emit(Result.Loading)
        emit(deleteTokenAndUser())
    }

    private suspend fun deleteTokenAndUser(): Result<Unit> {
        val saveUserResult = userRepository.clearLoggedInUser()
        val saveTokenResult = authRepository.clearAccessToken()

        return if (saveUserResult.success() && saveTokenResult.error()) {
            Result.Success(Unit)
        } else {
            resolveSaveError(saveUserResult, saveTokenResult)
        }
    }

    private fun resolveSaveError(
        saveUserResult: Result<*>,
        saveTokenResult: Result<*>
    ): Result<Unit> {
        return when {
            saveUserResult is Result.Error -> Result.Error(message = saveUserResult.asMessage())
            saveTokenResult is Result.Error -> Result.Error(message = saveTokenResult.asMessage())
            else -> Result.Error(message = resourcesProvider.getString(R.string.failed_logout))
        }
    }
}