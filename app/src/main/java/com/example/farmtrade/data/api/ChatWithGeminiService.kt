package com.example.farmtrade.data.api

import com.example.farmtrade.data.db.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatWithGeminiService {
    @POST("/api/v1/chat")
    suspend fun chatWithGemini(@Body message: String): ChatResponse
}