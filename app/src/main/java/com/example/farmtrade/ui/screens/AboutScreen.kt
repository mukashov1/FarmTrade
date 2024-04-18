package com.example.farmtrade.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import com.example.farmtrade.data.api.RetrofitInstance
import com.example.farmtrade.data.api.UploadImageService
import com.example.farmtrade.data.db.ImageResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

@Composable
fun AboutScreen() {
    val type = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }



    Column {

        Button(onClick = {
            sendImage { imageResponse ->
                type.value = imageResponse?.type ?: ""
                name.value = imageResponse?.name ?: ""
            }
        }) {
            Text(text = "Upload Image ")
        }
        Text(text = "TYPE: ${type.value}", fontSize = 30.sp)
        Text(text = "NAME: ${name.value}", fontSize = 30.sp)
    }
}

fun sendImage(callback: (ImageResponse?) -> Unit) {
    val api = RetrofitInstance.retrofit.create(UploadImageService::class.java)
    val imageFilePath = "Bakdaulet/DCIM/Restored/File.jpg" // Relative path of the image
    val imageFile = File(imageFilePath)
    val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

    val call = api.uploadImage(body)
    call.enqueue(object : retrofit2.Callback<ImageResponse> {
        override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
            if (response.isSuccessful) {
                val result = response.body()
                Log.i(TAG, "SUCCESS")
                callback(result)
            }
        }

        override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
            Log.e(TAG, "FAILURE ON REQUEST")
            Log.e(TAG, call.toString())
            Log.e(TAG, t.message.toString())
            callback(null)
        }
    })
}