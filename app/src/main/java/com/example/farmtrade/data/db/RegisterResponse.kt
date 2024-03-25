package com.example.farmtrade.data.db

import java.time.LocalDate

data class RegisterSuccessResponse(
    val accessToken: String,
    val refreshToken: String,
)

data class RegisterErrorResponse(
    val code: Int,
    val message: String,
    val timestamp: LocalDate,
)
