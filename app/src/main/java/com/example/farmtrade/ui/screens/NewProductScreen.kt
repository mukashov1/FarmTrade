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
//    uploadManual(context)
    Column(modifier = Modifier.fillMaxSize()) {
        ProductForm(context = context, onSubmit = { product, imageUris ->
            uploadProductToFirestoreAndStorage(product, imageUris) { success ->
                if (success) {
                } else {

                }
            }
        })
    }
}

//fun uploadManual(context: Context) {
//    val images = listOf(
//        listOf(R.drawable.apple, R.drawable.apple2),
//        listOf(R.drawable.honey2, R.drawable.honey5),
//        listOf(R.drawable.beef3, R.drawable.beef),
//        listOf(R.drawable.tomates, R.drawable.tomates5),
//        listOf(R.drawable.potato, R.drawable.potato4),
//        listOf(R.drawable.lambe, R.drawable.lamb),
//        listOf(R.drawable.flour, R.drawable.flour3),
//        listOf(R.drawable.trout, R.drawable.trout4),
//        listOf(R.drawable.sunoilfd, R.drawable.sunoil),
//        listOf(R.drawable.garlic, R.drawable.garlic4),
//        listOf(R.drawable.chickpeas, R.drawable.chickpeas56),
//        listOf(R.drawable.rice43, R.drawable.rice),
//        listOf(R.drawable.buckthorn, R.drawable.buckthorn2),
//        listOf(R.drawable.barley, R.drawable.barley4),
//        listOf(R.drawable.cucumber, R.drawable.cucumber2),
//        listOf(R.drawable.seeds, R.drawable.seeds2),
//        listOf(R.drawable.almonds, R.drawable.almonds2),
//        listOf(R.drawable.mutton, R.drawable.mutton2),
//        listOf(R.drawable.peppers, R.drawable.peppers2),
//        listOf(R.drawable.onions, R.drawable.onions2),
//    )
//    val productItems = listOf(
//        ProductItem(
//            1,
//            "Almaty Apples",
//            "Fresh, organic apples from Almaty orchards.",
//            "Fruits",
//            "Almaty Central Market",
//            500.0,
//            10.0,
//            450.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            2,
//            "Astana Honey",
//            "Pure wildflower honey from near Astana.",
//            "Sweeteners",
//            "Astana Grocery Store",
//            1500.0,
//            5.0,
//            1425.0,
//            "per liter",
//            mutableListOf()
//        ),
//        ProductItem(
//            3,
//            "Karaganda Beef",
//            "Grass-fed beef from the steppes of Karaganda.",
//            "Meat",
//            "Karaganda Butcher Shop",
//            2200.0,
//            15.0,
//            1870.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            4,
//            "Shymkent Tomatoes",
//            "Juicy, vine-ripened tomatoes grown in Shymkent.",
//            "Vegetables",
//            "Shymkent Farmers Market",
//            300.0,
//            8.0,
//            276.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            5,
//            "Pavlodar Potatoes",
//            "Earthy and flavorful potatoes from Pavlodar farms.",
//            "Root Vegetables",
//            "Pavlodar Retail Outlet",
//            200.0,
//            10.0,
//            180.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            6,
//            "Aktobe Lamb",
//            "Tender, free-range lamb meat from Aktobe's pastures.",
//            "Meat",
//            "Aktobe Meat Market",
//            2500.0,
//            12.0,
//            2200.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            7,
//            "Kostanay Wheat Flour",
//            "High-quality wheat flour, milled in Kostanay.",
//            "Grains",
//            "Kostanay Grocery Store",
//            360.0,
//            5.0,
//            342.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            8,
//            "Ust-Kamenogorsk Trout",
//            "Freshwater trout from rivers near Ust-Kamenogorsk.",
//            "Fish",
//            "Ust-Kamenogorsk Fish Market",
//            1800.0,
//            10.0,
//            1620.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            9,
//            "Semey Sunflower Oil",
//            "Cold-pressed sunflower oil produced in Semey.",
//            "Oils",
//            "Semey Supermarket",
//            800.0,
//            7.0,
//            744.0,
//            "per liter",
//            mutableListOf()
//        ),
//        ProductItem(
//            10,
//            "Atyrau Garlic",
//            "Aromatic garlic from Atyrau, known for robust flavor.",
//            "Vegetables",
//            "Atyrau Local Market",
//            600.0,
//            10.0,
//            540.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            11,
//            "Turkestan Chickpeas",
//            "Nutrient-rich chickpeas, perfect for stews and salads.",
//            "Legumes",
//            "Turkestan Organic Shop",
//            400.0,
//            15.0,
//            340.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            12,
//            "Kyzylorda Rice",
//            "Long-grain rice from Kyzylorda fields.",
//            "Grains",
//            "Kyzylorda Regional Store",
//            450.0,
//            5.0,
//            428.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            13,
//            "Aktau Sea Buckthorn",
//            "Vitamin-rich sea buckthorn berries from Aktau coasts.",
//            "Berries",
//            "Aktau Health Food Store",
//            1200.0,
//            10.0,
//            1080.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            14,
//            "Taldykorgan Barley",
//            "Organic barley for hearty soups from Taldykorgan.",
//            "Grains",
//            "Taldykorgan Grain Market",
//            280.0,
//            10.0,
//            252.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            15,
//            "Kokshetau Cucumbers",
//            "Crunchy and refreshing cucumbers from Kokshetau greenhouses.",
//            "Vegetables",
//            "Kokshetau Vegetable Store",
//            320.0,
//            5.0,
//            304.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            16,
//            "Petropavlovsk Seeds",
//            "Roasted sunflower seeds, snack-ready from Petropavlovsk.",
//            "Snacks",
//            "Petropavlovsk Snack Bar",
//            700.0,
//            8.0,
//            644.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            17,
//            "Taraz Almonds",
//            "Fresh, natural almonds from Taraz, great for heart health.",
//            "Nuts",
//            "Taraz Nut Shop",
//            2000.0,
//            10.0,
//            1800.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            18,
//            "Zhezkazgan Mutton",
//            "Premium mutton from the hills around Zhezkazgan.",
//            "Meat",
//            "Zhezkazgan Meat Market",
//            2400.0,
//            7.0,
//            2232.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            19,
//            "Oral Peppers",
//            "Bright and spicy peppers perfect for dishes from Oral.",
//            "Vegetables",
//            "Oral Spice Market",
//            550.0,
//            5.0,
//            523.0,
//            "per kg",
//            mutableListOf()
//        ),
//        ProductItem(
//            20,
//            "Ekibastuz Onions",
//            "Strong and flavorful onions, ideal for cooking from Ekibastuz.",
//            "Vegetables",
//            "Ekibastuz Grocery Store",
//            250.0,
//            10.0,
//            225.0,
//            "per kg",
//            mutableListOf()
//        )
//    )
//    for (i in 0..19) {
//        uploadProductItem(context, productItems[i], images[i])
//    }
//}

//fun uploadImageAndGetUrl(context: Context, imageResId: Int, completion: (String) -> Unit) {
//    val storageRef = FirebaseStorage.getInstance().reference
//    val filePath = "images/${UUID.randomUUID()}.jpg"
//    val imageRef = storageRef.child(filePath)
//
//
//    val bitmap = BitmapFactory.decodeResource(context.resources, imageResId)
//    val baos = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//    val data = baos.toByteArray()
//
//
//    imageRef.putBytes(data)
//        .addOnSuccessListener {
//            imageRef.downloadUrl.addOnSuccessListener { uri ->
//                completion(uri.toString())
//            }
//        }.addOnFailureListener {
//            // Handle any errors
//        }
//}

//fun uploadProductItem(context: Context, productItem: ProductItem, imageResIds: List<Int>) {
//    val db = FirebaseFirestore.getInstance()
//    val productRef = db.collection("products").document()
//
//    val imageUrls = mutableListOf<String>()
//    val imageUploadCount = imageResIds.size
//
//    imageResIds.forEach { resId ->
//        uploadImageAndGetUrl(context, resId) { imageUrl ->
//            imageUrls.add(imageUrl)
//            if (imageUrls.size == imageUploadCount) {
//                val updatedProductItem = productItem.copy(images = imageUrls.toMutableList())
//                productRef.set(updatedProductItem)
//                    .addOnSuccessListener {
//                        // Handle success
//                        println("SUCCESS")
//                    }.addOnFailureListener {
//                        // Handle failure
//                        println("FAILURE")
//                    }
//            }
//        }
//    }
//}

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

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { uris: List<Uri> ->
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

fun upImage() {
    val products = listOf(
        ProductItem(
            2,
            "Astana Honey",
            "Pure wildflower honey from near Astana.",
            "Sweeteners",
            "Astana Grocery Store",
            1500.0,
            5.0,
            1425.0,
            "per liter",
            mutableListOf("")
        ),
        ProductItem(
            3,
            "Karaganda Beef",
            "Grass-fed beef from the steppes of Karaganda.",
            "Meat",
            "Karaganda Butcher Shop",
            2200.0,
            15.0,
            1870.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            4,
            "Shymkent Tomatoes",
            "Juicy, vine-ripened tomatoes grown in Shymkent.",
            "Vegetables",
            "Shymkent Farmers Market",
            300.0,
            8.0,
            276.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            5,
            "Pavlodar Potatoes",
            "Earthy and flavorful potatoes from Pavlodar farms.",
            "Root Vegetables",
            "Pavlodar Retail Outlet",
            200.0,
            10.0,
            180.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            6,
            "Aktobe Lamb",
            "Tender, free-range lamb meat from Aktobe's pastures.",
            "Meat",
            "Aktobe Meat Market",
            2500.0,
            12.0,
            2200.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            7,
            "Kostanay Wheat Flour",
            "High-quality wheat flour, milled in Kostanay.",
            "Grains",
            "Kostanay Grocery Store",
            360.0,
            5.0,
            342.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            8,
            "Ust-Kamenogorsk Trout",
            "Freshwater trout from rivers near Ust-Kamenogorsk.",
            "Fish",
            "Ust-Kamenogorsk Fish Market",
            1800.0,
            10.0,
            1620.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            9,
            "Semey Sunflower Oil",
            "Cold-pressed sunflower oil produced in Semey.",
            "Oils",
            "Semey Supermarket",
            800.0,
            7.0,
            744.0,
            "per liter",
            mutableListOf("")
        ),
        ProductItem(
            10,
            "Atyrau Garlic",
            "Aromatic garlic from Atyrau, known for robust flavor.",
            "Vegetables",
            "Atyrau Local Market",
            600.0,
            10.0,
            540.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            11,
            "Turkestan Chickpeas",
            "Nutrient-rich chickpeas, perfect for stews and salads.",
            "Legumes",
            "Turkestan Organic Shop",
            400.0,
            15.0,
            340.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            12,
            "Kyzylorda Rice",
            "Long-grain rice from Kyzylorda fields.",
            "Grains",
            "Kyzylorda Regional Store",
            450.0,
            5.0,
            428.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            13,
            "Aktau Sea Buckthorn",
            "Vitamin-rich sea buckthorn berries from Aktau coasts.",
            "Berries",
            "Aktau Health Food Store",
            1200.0,
            10.0,
            1080.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            14,
            "Taldykorgan Barley",
            "Organic barley for hearty soups from Taldykorgan.",
            "Grains",
            "Taldykorgan Grain Market",
            280.0,
            10.0,
            252.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            15,
            "Kokshetau Cucumbers",
            "Crunchy and refreshing cucumbers from Kokshetau greenhouses.",
            "Vegetables",
            "Kokshetau Vegetable Store",
            320.0,
            5.0,
            304.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            16,
            "Petropavlovsk Seeds",
            "Roasted sunflower seeds, snack-ready from Petropavlovsk.",
            "Snacks",
            "Petropavlovsk Snack Bar",
            700.0,
            8.0,
            644.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            17,
            "Taraz Almonds",
            "Fresh, natural almonds from Taraz, great for heart health.",
            "Nuts",
            "Taraz Nut Shop",
            2000.0,
            10.0,
            1800.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            18,
            "Zhezkazgan Mutton",
            "Premium mutton from the hills around Zhezkazgan.",
            "Meat",
            "Zhezkazgan Meat Market",
            2400.0,
            7.0,
            2232.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            19,
            "Oral Peppers",
            "Bright and spicy peppers perfect for dishes from Oral.",
            "Vegetables",
            "Oral Spice Market",
            550.0,
            5.0,
            523.0,
            "per kg",
            mutableListOf("")
        ),
        ProductItem(
            20,
            "Ekibastuz Onions",
            "Strong and flavorful onions, ideal for cooking from Ekibastuz.",
            "Vegetables",
            "Ekibastuz Grocery Store",
            250.0,
            10.0,
            225.0,
            "per kg",
            mutableListOf()
        )
    )
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