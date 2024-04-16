package com.example.farmtrade.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatWithGeminiService {
    @GET("/api/v1/chat")
    suspend fun chatWithGemini(@Query("message") message: String): ResponseBody
}