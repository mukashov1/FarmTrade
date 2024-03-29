package com.example.farmtrade.data.api

import com.example.farmtrade.data.db.RegisterResponse
import com.example.farmtrade.data.db.User
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("/api/v1/auth/register")
    suspend fun registerUser(@Body user: User): RegisterResponse
}