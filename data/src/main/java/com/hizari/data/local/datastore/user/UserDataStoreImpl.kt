package com.hizari.data.local.datastore.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hizari.common.data.Result
import com.hizari.common.util.FSLog
import com.hizari.data.R
import com.hizari.data.model.dto.user.UserDTO
import com.hizari.domain.provider.ResourcesProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Fake Store - com.hizari.data.local.datastore.user
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

private const val DATA_STORE_NAME = "user_data"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

@Singleton
class UserDataStoreImpl(
    @ApplicationContext private val context: Context,
    private val resourcesProvider: ResourcesProvider
) : UserDataStore {

    private object PreferencesKeys {
        val USER_DATA = stringPreferencesKey("user_data")
    }

    private val dataStore = context.dataStore

    override suspend fun setUserData(dto: UserDTO): Result<UserDTO> {
        try {
            val jsonData = Json.encodeToString(dto)

            dataStore.edit { preferences ->
                preferences[PreferencesKeys.USER_DATA] = jsonData
            }
            return getUserData()
        } catch (e: Exception) {
            FSLog.e("Error setting user data = ${e.message}")
            return Result.Error(throwable = e)
        }
    }

    override suspend fun getUserData(): Result<UserDTO> {
        val jsonData = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER_DATA]
        }.firstOrNull()

        if (jsonData.isNullOrEmpty()) return Result.Error(
            throwable = Exception(resourcesProvider.getString(R.string.local_user_data_is_empty))
        )

        try {
            return Result.Success(Json.decodeFromString(jsonData))
        } catch (e: Exception) {
            FSLog.e("Error getting user data = ${e.message}")
            return Result.Error(throwable = e)
        }
    }

    override suspend fun clearUserData(): Result<Boolean> {
        return try {
            dataStore.edit { preferences ->
                preferences.remove(PreferencesKeys.USER_DATA)
            }

            val clearedDataResult = getUserData()
            return if (clearedDataResult is Result.Error) {
                Result.Success(true)
            } else {
                Result.Error(
                    throwable = Exception(resourcesProvider.getString(R.string.failed_to_clear_user_data))
                )
            }
        } catch (e: Exception) {
            FSLog.e("Error clearing user data = ${e.message}")
            Result.Error(throwable = e)
        }
    }

    override fun observeUserData(): Flow<Result<UserDTO>> = flow {
        dataStore.data.collect { preferences ->
            val jsonData = preferences[PreferencesKeys.USER_DATA]

            if (jsonData.isNullOrEmpty()) {
                emit(
                    Result.Error(
                        throwable = Exception(resourcesProvider.getString(R.string.local_user_data_is_empty))
                    )
                )
            } else {
                try {
                    val userData = Json.decodeFromString<UserDTO>(jsonData)
                    emit(Result.Success(userData))
                } catch (e: Exception) {
                    FSLog.e("Error getting user data = ${e.message}")
                    emit(Result.Error(throwable = e))
                }
            }
        }
    }
}
