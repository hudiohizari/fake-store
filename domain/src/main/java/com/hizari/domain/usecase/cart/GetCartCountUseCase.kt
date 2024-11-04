package com.hizari.domain.usecase.cart

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.domain.model.user.User
import com.hizari.domain.repository.cart.CartRepository
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

class GetCartCountUseCase(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Result<Int>> = flow {
        emit(Result.Loading)

        val userResult = userRepository.getLoggedInUser()
        val result = processUserResult(userResult)

        emit(result)
    }

    private suspend fun processUserResult(userResult: Result<User>): Result<Int> {
        return handleResult(
            resultCall = { userResult },
            onSuccess = { user ->
                cartRepository.getCartCountByUserId(userId = user.id)
            },
            onError = { code, message, throwable ->
                Result.Error(code, message, throwable)
            }
        )
    }

}