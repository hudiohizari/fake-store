package com.hizari.data.network.service

import com.hizari.common.util.Constant
import com.hizari.data.model.dto.product.ProductDTO
import com.hizari.data.network.util.Client
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Fake Store - com.hizari.data.network.service
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

interface ProductService {

    @POST("products/categories")
    suspend fun getProductCategoryList(): Response<List<String>>

    @POST("products/")
    suspend fun getProductList(): Response<List<ProductDTO>>

    @POST("products/category/{category}")
    suspend fun getProductListByCategory(
        @Path("category") category: String
    ): Response<List<ProductDTO>>

    companion object {
        operator fun invoke(client: Client): UserService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.URL.BASE_URL)
                .client(client.provideClient())
                .build()
                .create(UserService::class.java)
        }
    }
}
