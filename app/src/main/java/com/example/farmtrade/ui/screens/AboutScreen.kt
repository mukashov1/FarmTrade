package com.example.farmtrade.ui.screens

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.farmtrade.data.api.RetrofitInstance
import com.example.farmtrade.data.api.UploadImageService
import com.example.farmtrade.data.db.ImageResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


@Composable
fun AboutScreen() {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var type by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            uri?.let {
                sendImage(context, it, onResult = { imageResponse ->
                    type = imageResponse?.type ?: ""
                    name = imageResponse?.name ?: ""
                })
            }
        }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text(text = "Pick Image")
        }
        imageUri?.let {
            // Display the image or use Coil to load the image
        }
        Text(text = "TYPE: $type", fontSize = 30.sp)
        Text(text = "NAME: $name", fontSize = 30.sp)
    }
}


@SuppressLint("Recycle")
fun sendImage(context: Context, uri: Uri, onResult: (ImageResponse?) -> Unit) {
    val api = RetrofitInstance.retrofit.create(UploadImageService::class.java)

    // Convert Uri to File
    val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r", null)
    val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
    val file = File(context.cacheDir, context.contentResolver.getFileName(uri))
    val outputStream = FileOutputStream(file)
    inputStream.copyTo(outputStream)

    // Create RequestBody instance from file
    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

    // Make the network call
    val call = api.uploadImage(body)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                val result = response.body()?.string()
                val imageResponse = Gson().fromJson(result, ImageResponse::class.java)
                Log.i(TAG, "Upload success")
                onResult(imageResponse)
            } else {
                Log.e(TAG, "Upload error: ${response.errorBody()?.string()}")
                onResult(null)
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.e(TAG, "Upload failure", t)
            onResult(null)
        }
    })
}


// Extension function to get file name from Uri
@SuppressLint("Range")
fun ContentResolver.getFileName(uri: Uri): String {
    var name = ""
    val cursor = query(uri, null, null, null, null)
    cursor?.use {
        it.moveToFirst()
        name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}