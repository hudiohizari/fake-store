package com.hizari.data.network.util

import android.content.Context
import com.hizari.common.data.Result
import com.hizari.data.R
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * Fake Store - com.hizari.data.network.util
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

abstract class SafeApiRequest {
    @Inject
    @ApplicationContext
    lateinit var context: Context

    suspend fun <T : Any> apiRequest(
        call: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error(
                    code = HttpURLConnection.HTTP_NO_CONTENT,
                    message = "Response body is null"
                )
            } else {
                val responseErrorBody = response.errorBody()?.string()
                val responseCode = response.code()
                parseErrorMessage(responseCode, responseErrorBody)
            }
        } catch (e: HttpException) {
            Result.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            Result.Error(throwable = e)
        }
    }


    private fun <T : Any> parseErrorMessage(code: Int, errorBody: String?): Result<T> {
        return if (code >= HttpURLConnection.HTTP_INTERNAL_ERROR) {
            Result.Error(code, context.getString(R.string.server_down))
        } else {
            errorBody?.let {
                Result.Error(code, it)
            } ?: run {
                Result.Error(code, context.getString(R.string.server_down))
            }
        }
    }
}