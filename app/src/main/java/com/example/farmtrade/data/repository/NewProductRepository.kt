package com.example.farmtrade.data.repository

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.example.farmtrade.data.api.RetrofitInstance
import com.example.farmtrade.data.api.UploadImageService
import com.example.farmtrade.data.db.ImageResponse
import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class NewProductRepository {
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    suspend fun uploadProductWithImages(product: ProductItem, imageUris: List<Uri>): Boolean {
        val docRef = db.collection("products").document()
        val imagePaths = uploadImages(docRef.id, imageUris)
        return if (imagePaths.isNotEmpty()) {
            val updatedProduct = product.copy(images = imagePaths)
            try {
                docRef.set(updatedProduct).await()
                true
            } catch (e: Exception) {
                false
            }
        } else false
    }

    private suspend fun uploadImages(productId: String, imageUris: List<Uri>): List<String> {
        return imageUris.mapNotNull { uri ->
            val ref = storage.reference.child("images/$productId/${uri.lastPathSegment}")
            val urlTask = ref.putFile(uri).continueWithTask { task ->
                if (!task.isSuccessful) task.exception?.let { throw it }
                ref.downloadUrl
            }
            urlTask.await().toString()
        }
    }

    fun sendImageForAutoFill(
        file: File,
        onResult: (Boolean, ImageResponse) -> Unit
    ) {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val call =
            RetrofitInstance.retrofit.create(UploadImageService::class.java).uploadImage(body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val result = response.body()?.string()
                    if (result != null && isJson(result)) {
                        // If the response is JSON, parse it to ImageResponse
                        val imageResponse = Gson().fromJson(result, ImageResponse::class.java)
                        Log.i(TAG, "Upload success")
                        onResult(true, imageResponse)
                    } else {
                        // If the response is plain text, handle it accordingly
                        Log.i(TAG, "Plain text response: $result")
                        onResult(false, ImageResponse("", ""))
                    }
                } else {
                    Log.e(TAG, "Upload error: ${response.errorBody()?.string()}")
                    onResult(false, ImageResponse("", ""))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "Upload failure", t)
                onResult(false, ImageResponse("", ""))
            }
        })
    }

    fun isJson(jsonString: String): Boolean {
        return try {
            Gson().fromJson(jsonString, Any::class.java)
            true
        } catch (e: JsonSyntaxException) {
            false
        }
    }
}