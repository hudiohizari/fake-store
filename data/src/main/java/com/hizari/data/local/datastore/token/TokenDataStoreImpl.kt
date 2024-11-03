package com.hizari.data.local.datastore.token

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hizari.common.data.Result
import com.hizari.common.util.FSLog
import com.hizari.data.R
import com.hizari.data.model.dto.auth.TokenDTO
import com.hizari.domain.provider.ResourcesProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Fake Store - com.hizari.data.local.datastore.token
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

private const val DATA_STORE_NAME = "token_data"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

@Singleton
class TokenDataStoreImpl(
    @ApplicationContext private val context: Context,
    private val resourcesProvider: ResourcesProvider
) : TokenDataStore {

    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    private val dataStore = context.dataStore

    override suspend fun setAccessToken(dto: TokenDTO): Result<TokenDTO> {
        try {
            val jsonData = Json.encodeToString(dto)

            dataStore.edit { preferences ->
                preferences[PreferencesKeys.ACCESS_TOKEN] = jsonData
            }
            return getAccessToken()
        } catch (e: Exception) {
            FSLog.e("Error setting access token = ${e.message}")
            return Result.Error(throwable = e)
        }
    }

    override suspend fun getAccessToken(): Result<TokenDTO> {
        val jsonData = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN]
        }.firstOrNull()

        if (jsonData.isNullOrEmpty()) return Result.Error(
            throwable = Exception(resourcesProvider.getString(R.string.access_token_is_empty))
        )

        try {
            return Result.Success(Json.decodeFromString(jsonData))
        } catch (e: Exception) {
            FSLog.e("Error getting access token = ${e.message}")
            return Result.Error(throwable = e)
        }
    }

    override suspend fun clearAccessToken(): Result<Boolean> {
        return try {
            dataStore.edit { preferences ->
                preferences.remove(PreferencesKeys.ACCESS_TOKEN)
            }

            val accessToken = getAccessToken()
            return if (accessToken is Result.Error) {
                Result.Success(true)
            } else {
                Result.Error(
                    throwable = Exception(resourcesProvider.getString(R.string.failed_to_clear_access_token))
                )
            }
        } catch (e: Exception) {
            FSLog.e("Error clearing tokens = ${e.message}")
            Result.Error(throwable = e)

        }
    }

}