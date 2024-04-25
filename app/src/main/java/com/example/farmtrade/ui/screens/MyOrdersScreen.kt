package com.example.farmtrade.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farmtrade.R
import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

@Composable
fun MyOrdersScreen(navController: NavController) {
//    Column {
//        Row {
//            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "BackArrow")
//            Text(text = "My Orders")
//        }
//        Divider()
//        Card {
//            Row {
//                Text(text = "Order ID")
//                Text(text = "54545")
//            }
//        }
//    }
    ScaffoldWithBottomBarAndCutout()
}

@Composable
fun ScaffoldWithBottomBarAndCutout() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("My Orders") }) },
        content = { padding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(padding)
                    .scrollable(scrollState, orientation = Orientation.Vertical)
            ) {
                repeat(100) {
                    Card(
                        backgroundColor = Color.Green,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    )
}


fun uploadManual(context: Context) {
    val images = listOf(
        listOf(R.drawable.apple, R.drawable.apple2),
        listOf(R.drawable.honey2, R.drawable.honey5),
        listOf(R.drawable.beef3, R.drawable.beef),
        listOf(R.drawable.tomates, R.drawable.tomates5),
        listOf(R.drawable.potato, R.drawable.potato4),
        listOf(R.drawable.lambe, R.drawable.lamb),
        listOf(R.drawable.flour, R.drawable.flour3),
        listOf(R.drawable.trout, R.drawable.trout4),
        listOf(R.drawable.sunoilfd, R.drawable.sunoil),
        listOf(R.drawable.garlic, R.drawable.garlic4),
        listOf(R.drawable.chickpeas, R.drawable.chickpeas56),
        listOf(R.drawable.rice43, R.drawable.rice),
        listOf(R.drawable.buckthorn, R.drawable.buckthorn2),
        listOf(R.drawable.barley, R.drawable.barley4),
        listOf(R.drawable.cucumber, R.drawable.cucumber2),
        listOf(R.drawable.seeds, R.drawable.seeds2),
        listOf(R.drawable.almonds, R.drawable.almonds2),
        listOf(R.drawable.mutton, R.drawable.mutton2),
        listOf(R.drawable.peppers, R.drawable.peppers2),
        listOf(R.drawable.onions, R.drawable.onions2),
    )
    val productItems = listOf(
        ProductItem(
            title = "Almaty Apples",
            description = "Fresh, organic apples from Almaty orchards.",
            category = "Fruits",
            place = "Almaty Central Market",
            price = 500.0,
            discount = 10.0,
            priceWithDiscount = 450.0,
            priceUnit = "per kg",
            images= mutableListOf()
        ),
        ProductItem(
            title = "Astana Honey",
            description = "Pure wildflower honey from near Astana.",
            category = "Sweeteners",
            place = "Astana Grocery Store",
            price = 1500.0,
            discount = 5.0,
            priceWithDiscount = 1425.0,
            priceUnit = "per liter",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Karaganda Beef",
            description = "Grass-fed beef from the steppes of Karaganda.",
            category = "Meat",
            place = "Karaganda Butcher Shop",
            price = 2200.0,
            discount = 15.0,
            priceWithDiscount = 1870.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Shymkent Tomatoes",
            description = "Juicy, vine-ripened tomatoes grown in Shymkent.",
            category = "Vegetables",
            place = "Shymkent Farmers Market",
            price = 300.0,
            discount = 8.0,
            priceWithDiscount = 276.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Pavlodar Potatoes",
            description = "Earthy and flavorful potatoes from Pavlodar farms.",
            category = "Root Vegetables",
            place = "Pavlodar Retail Outlet",
            price = 200.0,
            discount = 10.0,
            priceWithDiscount = 180.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Aktobe Lamb",
            description = "Tender, free-range lamb meat from Aktobe's pastures.",
            category = "Meat",
            place = "Aktobe Meat Market",
            price = 2500.0,
            discount = 12.0,
            priceWithDiscount = 2200.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Kostanay Wheat Flour",
            description = "High-quality wheat flour, milled in Kostanay.",
            category = "Grains",
            place = "Kostanay Grocery Store",
            price = 360.0,
            discount = 5.0,
            priceWithDiscount = 342.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Ust-Kamenogorsk Trout",
            description = "Freshwater trout from rivers near Ust-Kamenogorsk.",
            category = "Fish",
            place = "Ust-Kamenogorsk Fish Market",
            price = 1800.0,
            discount = 10.0,
            priceWithDiscount = 1620.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Semey Sunflower Oil",
            description = "Cold-pressed sunflower oil produced in Semey.",
            category = "Oils",
            place = "Semey Supermarket",
            price = 800.0,
            discount = 7.0,
            priceWithDiscount = 744.0,
            priceUnit = "per liter",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Atyrau Garlic",
            description = "Aromatic garlic from Atyrau, known for robust flavor.",
            category = "Vegetables",
            place = "Atyrau Local Market",
            price = 600.0,
            discount = 10.0,
            priceWithDiscount = 540.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Turkestan Chickpeas",
            description = "Nutrient-rich chickpeas, perfect for stews and salads.",
            category = "Legumes",
            place = "Turkestan Organic Shop",
            price = 400.0,
            discount = 15.0,
            priceWithDiscount = 340.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Kyzylorda Rice",
            description = "Long-grain rice from Kyzylorda fields.",
            category = "Grains",
            place = "Kyzylorda Regional Store",
            price = 450.0,
            discount = 5.0,
            priceWithDiscount = 428.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Aktau Sea Buckthorn",
            description = "Vitamin-rich sea buckthorn berries from Aktau coasts.",
            category = "Berries",
            place = "Aktau Health Food Store",
            price = 1200.0,
            discount = 10.0,
            priceWithDiscount = 1080.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Taldykorgan Barley",
            description = "Organic barley for hearty soups from Taldykorgan.",
            category = "Grains",
            place = "Taldykorgan Grain Market",
            price = 280.0,
            discount = 10.0,
            priceWithDiscount = 252.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Kokshetau Cucumbers",
            description = "Crunchy and refreshing cucumbers from Kokshetau greenhouses.",
            category = "Vegetables",
            place = "Kokshetau Vegetable Store",
            price = 320.0,
            discount = 5.0,
            priceWithDiscount = 304.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Petropavlovsk Seeds",
            description = "Roasted sunflower seeds, snack-ready from Petropavlovsk.",
            category = "Snacks",
            place = "Petropavlovsk Snack Bar",
            price = 700.0,
            discount = 8.0,
            priceWithDiscount = 644.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Taraz Almonds",
            description = "Fresh, natural almonds from Taraz, great for heart health.",
            category = "Nuts",
            place = "Taraz Nut Shop",
            price = 2000.0,
            discount = 10.0,
            priceWithDiscount = 1800.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Zhezkazgan Mutton",
            description = "Premium mutton from the hills around Zhezkazgan.",
            category = "Meat",
            place = "Zhezkazgan Meat Market",
            price = 2400.0,
            discount = 7.0,
            priceWithDiscount = 2232.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Oral Peppers",
            description = "Bright and spicy peppers perfect for dishes from Oral.",
            category = "Vegetables",
            place = "Oral Spice Market",
            price = 550.0,
            discount = 5.0,
            priceWithDiscount = 523.0,
            priceUnit = "per kg",
            images = mutableListOf()
        ),
        ProductItem(
            title = "Ekibastuz Onions",
            description = "Strong and flavorful onions, ideal for cooking from Ekibastuz.",
            category = "Vegetables",
            place = "Ekibastuz Grocery Store",
            price = 250.0,
            discount = 10.0,
            priceWithDiscount = 225.0,
            priceUnit = "per kg",
            images = mutableListOf()
        )
    )
    for (i in 0..19) {
        uploadProductItem(context, productItems[i], images[i])
    }
}

fun uploadImageAndGetUrl(context: Context, imageResId: Int, completion: (String) -> Unit) {
    val storageRef = FirebaseStorage.getInstance().reference
    val filePath = "images/${UUID.randomUUID()}.jpg"
    val imageRef = storageRef.child(filePath)


    val bitmap = BitmapFactory.decodeResource(context.resources, imageResId)
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()


    imageRef.putBytes(data)
        .addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                completion(uri.toString())
            }
        }.addOnFailureListener {
            // Handle any errors
        }
}

fun uploadProductItem(context: Context, productItem: ProductItem, imageResIds: List<Int>) {
    val db = FirebaseFirestore.getInstance()
    val productRef = db.collection("products").document()

    val imageUrls = mutableListOf<String>()
    val imageUploadCount = imageResIds.size

    imageResIds.forEach { resId ->
        uploadImageAndGetUrl(context, resId) { imageUrl ->
            imageUrls.add(imageUrl)
            if (imageUrls.size == imageUploadCount) {
                val updatedProductItem = productItem.copy(images = imageUrls.toMutableList(), id = productRef.id)
                productRef.set(updatedProductItem)
                    .addOnSuccessListener {
                        // Handle success
                        println("SUCCESS")
                    }.addOnFailureListener {
                        // Handle failure
                        println("FAILURE")
                    }
            }
        }
    }
}

@Preview
@Composable
fun DefualPreviiew() {
ScaffoldWithBottomBarAndCutout()
}