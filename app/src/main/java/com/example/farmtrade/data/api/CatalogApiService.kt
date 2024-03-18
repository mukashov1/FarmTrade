package com.example.farmtrade.data.api

import com.example.farmtrade.data.db.CatalogResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CatalogApiService {
    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getCatalogItems(): Response<CatalogResponse>

    object RetrofitInstance {
        val api: CatalogApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatalogApiService::class.java)
        }
    }
}
