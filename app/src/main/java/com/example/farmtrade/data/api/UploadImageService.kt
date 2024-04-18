package com.example.farmtrade.data.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadImageService {
    @Multipart
    @POST("api/v1/images/upload")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<ResponseBody>
}