package com.example.farmtrade.ui.screens

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.farmtrade.data.api.RetrofitInstance
import com.example.farmtrade.data.api.UploadImageService
import com.example.farmtrade.data.db.ImageResponse
import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
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
import java.util.Locale


@Composable
fun NewProductScreen() {
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxSize()){
        ProductForm(context = context, onSubmit = { product, imageUris ->
            uploadProductToFirestoreAndStorage(product, imageUris) { success ->
                if (success) {
                } else {

                }
            }
        })
    }

}

@Composable
fun ProductForm(context: Context, onSubmit: (ProductItem, List<Uri>) -> Unit) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var discount by remember { mutableDoubleStateOf(0.0) }
    var place by remember { mutableStateOf("") }
    var price by remember { mutableDoubleStateOf(0.0) }
    var priceUnit by remember { mutableStateOf("") }
    val imageUris = remember { mutableStateListOf<Uri>() }

    // Function to handle image response
    fun handleImageResponse(response: ImageResponse?) {
        if (response != null) {
            name = response.name.capitalize(Locale.ROOT)
            category = response.type.capitalize(Locale.ROOT)
        }
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { uris: List<Uri> ->
        imageUris.addAll(uris)
        if (uris.isNotEmpty()) {
            sendImage(context, uris.first(), ::handleImageResponse)
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Create New Product",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .fillMaxWidth()
            )
        }
        Divider()
        TextField(value = name, onValueChange = { name = it }, label = { Text("Product Name") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = discount.toString(),
            onValueChange = { discount = it.toDouble() },
            label = { Text("Discount (%)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = place, onValueChange = { place = it }, label = { Text("Place") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = price.toString(),
            onValueChange = { price = it.toDouble() },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = priceUnit,
            onValueChange = { priceUnit = it },
            label = { Text("Price Unit") }
        )

        Button(onClick = { launcher.launch("image/*") }) {
            Text("Select Images")
        }

        // Wrap LazyColumn with a fixed height and make it scrollable
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow {
                items(imageUris) { uri ->
                    Image(
                        painter = rememberImagePainter(uri),
                        contentDescription = "Selected Image",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                val product = ProductItem(
                    title = name,
                    category = category,
                    description = description,
                    discount = discount,
                    id = 0,  // ID will be generated by Firestore
                    place = place,
                    price = price,
                    priceWithDiscount = calculatePriceWithDiscount(price, discount),
                    priceUnit = priceUnit,
                    images = imageUris.map { it.toString() }.toMutableList()
                )
                onSubmit(product, imageUris)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Submit")
        }
    }
}

fun calculatePriceWithDiscount(price: Double, discount: Double): Double {
    return price - (price * discount / 100)
}

fun uploadProductToFirestoreAndStorage(
    product: ProductItem,
    imageUris: List<Uri>,
    onCompletion: (Boolean) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    val productRef = db.collection("products").document()


    val imagePaths = mutableListOf<String>()
    var uploadsRemaining = imageUris.size


    if (imageUris.isEmpty()) {
        // Handle case where no images are selected
        uploadProductData(product.copy(images = imagePaths), productRef, onCompletion)
    } else {
        for (uri in imageUris) {
            val imageRef = storage.reference.child(
                "images/${productRef.id}/${
                    uri.path?.let { File(it).name }
                }"
            )
            imageRef.putFile(uri).continueWithTask { task ->
                if (!task.isSuccessful) task.exception?.let { throw it }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    imagePaths.add(task.result.toString())
                    uploadsRemaining--
                    if (uploadsRemaining == 0) {
                        uploadProductData(
                            product.copy(images = imagePaths),
                            productRef,
                            onCompletion
                        )
                    }
                } else {
                    onCompletion(false)
                }
            }
        }
    }
}

fun uploadProductData(
    product: ProductItem,
    productRef: DocumentReference,
    onCompletion: (Boolean) -> Unit
) {
    productRef.set(product).addOnSuccessListener {
        Log.i(TAG, "SUCCESS IN UPLOAD")
        onCompletion(true)
    }.addOnFailureListener {
        Log.i(TAG, "Error IN UPLOAD")
        onCompletion(false)
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
                if (result != null && isJson(result)) {
                    // If the response is JSON, parse it to ImageResponse
                    val imageResponse = Gson().fromJson(result, ImageResponse::class.java)
                    Log.i(TAG, "Upload success")
                    onResult(imageResponse)
                } else {
                    // If the response is plain text, handle it accordingly
                    Log.i(TAG, "Plain text response: $result")
                    onResult(ImageResponse("", ""))
                    Toast.makeText(context, "Can you try with another image?", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Log.e(TAG, "Upload error: ${response.errorBody()?.string()}")
                onResult(ImageResponse("", ""))
                Toast.makeText(context, "Can you try with another image?", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.e(TAG, "Upload failure", t)
            onResult(ImageResponse("", ""))
            Toast.makeText(context, "Can you try later?", Toast.LENGTH_SHORT).show()
        }
    })

    // Function to check if a string is JSON
    fun isJson(jsonString: String): Boolean {
        return try {
            Gson().fromJson(jsonString, Any::class.java)
            true
        } catch (e: JsonSyntaxException) {
            false
        }
    }

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

fun isJson(jsonString: String): Boolean {
    return try {
        Gson().fromJson(jsonString, Any::class.java)
        true
    } catch (e: JsonSyntaxException) {
        false
    }
}