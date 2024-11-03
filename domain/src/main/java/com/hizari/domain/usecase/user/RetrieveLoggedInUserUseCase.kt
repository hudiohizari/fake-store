package com.hizari.domain.usecase.user

import com.hizari.common.data.Result
import com.hizari.domain.model.user.User
import com.hizari.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * SPPI - com.hizari.domain.usecase.auth
 *
 * Created by hudiohizari on 26/09/24.
 * https://github.com/hudiohizari
 *
 */

class RetrieveLoggedInUserUseCase(private val userRepository: UserRepository) {

    operator fun invoke(): Flow<Result<User>> = flow {
        emit(Result.Loading)
        emit(userRepository.getLoggedInUser())
    }
}