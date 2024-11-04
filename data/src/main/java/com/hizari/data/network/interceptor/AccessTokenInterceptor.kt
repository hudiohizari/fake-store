package com.hizari.data.network.interceptor

import com.hizari.common.data.Result
import com.hizari.data.local.datastore.token.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Fake Store - com.hizari.data.network.interceptor
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

class AccessTokenInterceptor(
    private val tokenDataStore: TokenDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val tokenResult = runBlocking { tokenDataStore.getAccessToken() }

        return when (tokenResult) {
            is Result.Success -> {
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer ${tokenResult.data.token}")
                    .build()
                chain.proceed(newRequest)
            }

            else -> chain.proceed(request)
        }
    }
}