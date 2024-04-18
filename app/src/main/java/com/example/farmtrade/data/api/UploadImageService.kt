package com.example.farmtrade.data.api

import com.example.farmtrade.data.db.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadImageService {
    @Multipart
    @POST("api/v1/images/upload")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<ImageResponse>
}