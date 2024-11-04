package com.hizari.domain.usecase.cart

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.domain.R
import com.hizari.domain.model.auth.Token
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.user.User
import com.hizari.domain.provider.ResourcesProvider
import com.hizari.domain.repository.auth.AuthRepository
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

class GetCartUseCase(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Result<Cart>> = flow {
        emit(Result.Loading)

        val userResult = userRepository.getLoggedInUser()
        val result = processUserResult(userResult)

        emit(result)
    }

    private suspend fun processUserResult(userResult: Result<User>): Result<Cart> {
        return handleResult(
            resultCall = { userResult },
            onSuccess = { user ->
                val cartResult = cartRepository.getCartByUserId(userId = user.id)
                processCartResult(cartResult)
            },
            onError = { code, message, throwable ->
                Result.Error(code, message, throwable)
            }
        )
    }

    private suspend fun processCartResult(cartResult: Result<Cart>): Result<Cart> {
        return handleResult(
            resultCall = { cartResult },
            onSuccess = { cart ->
                Result.Success(cart)
            },
            onError = { code, message, throwable ->
                Result.Error(code, message, throwable)
            }
        )
    }

}