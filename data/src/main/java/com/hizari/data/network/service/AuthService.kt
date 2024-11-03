package com.hizari.data.network.service

import com.hizari.common.util.Constant
import com.hizari.data.model.dto.auth.TokenDTO
import com.hizari.data.model.request.auth.PostLoginRequest
import com.hizari.data.network.util.Client
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Fake Store - com.hizari.data.network.service
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

interface AuthService {

    @POST("auth/login")
    suspend fun postLogin(
        @Body body: PostLoginRequest,
    ): Response<TokenDTO>

    companion object {
        operator fun invoke(client: Client): AuthService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.URL.BASE_URL)
                .client(client.provideClient())
                .build()
                .create(AuthService::class.java)
        }
    }
}