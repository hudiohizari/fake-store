package com.hizari.data.repository.user

import com.hizari.common.data.Result
import com.hizari.common.extention.handleResult
import com.hizari.data.local.datastore.user.UserDataStore
import com.hizari.data.mapper.user.toDTO
import com.hizari.data.mapper.user.toDomain
import com.hizari.data.network.service.UserService
import com.hizari.data.network.util.SafeApiRequest
import com.hizari.domain.model.user.User
import com.hizari.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Fake Store - com.hizari.data.repository.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userDataStore: UserDataStore,
) : UserRepository, SafeApiRequest() {
    override suspend fun getCurrentUser(): Result<User> {
        // Cannot find current logged in user id from the API docs, so return mock
        return Result.Success(User.mock())
    }

    override suspend fun setLoggedInUser(user: User): Result<Unit> {
        return handleResult(
            resultCall = {
                userDataStore.setUserData(user.toDTO())
            },
            onSuccess = {
                Result.Success(Unit)
            }
        )
    }

    override suspend fun getLoggedInUser(): Result<User> {
        return handleResult(
            resultCall = {
                userDataStore.getUserData()
            },
            onSuccess = {
                Result.Success(it.toDomain())
            }
        )
    }

    override suspend fun clearLoggedInUser(): Result<Unit> {
        return handleResult(
            resultCall = {
                userDataStore.clearUserData()
            },
            onSuccess = {
                Result.Success(Unit)
            }
        )
    }

    /**
     * Observe function
     * */
    override fun observeLoggedInUser(): Flow<Result<User>> {
        return userDataStore.observeUserData().map { res ->
            handleResult(
                resultCall = {
                    res
                },
                onSuccess = {
                    Result.Success(it.toDomain())
                }
            )
        }
    }

}