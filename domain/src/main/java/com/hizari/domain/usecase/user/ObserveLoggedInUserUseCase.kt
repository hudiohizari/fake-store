package com.hizari.domain.usecase.user

import com.hizari.common.data.Result
import com.hizari.domain.model.user.User
import com.hizari.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * Fake Store - com.hizari.domain.usecase.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

class ObserveLoggedInUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Result<User>> {
        return userRepository.observeLoggedInUser()
    }
}