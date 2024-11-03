package com.hizari.data.network.util

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Fake Store - com.hizari.data.network.util
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

class Client(private val chuckerInterceptor: ChuckerInterceptor) {

    fun provideClient(): OkHttpClient {
        val interceptorLogging = HttpLoggingInterceptor()
        interceptorLogging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptorLogging)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}