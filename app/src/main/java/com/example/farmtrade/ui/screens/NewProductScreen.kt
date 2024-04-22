package com.example.farmtrade.ui.screens

import android.net.Uri
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.ui.viewmodels.NewProductViewModel

@Composable
fun NewProductScreen(viewModel: NewProductViewModel) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.submitSuccess == true) {
        Toast.makeText(LocalContext.current, "The product is uploaded!!!", Toast.LENGTH_LONG).show()
    }

    if (uiState.isLoading) {
        println("isLoading is TRUE")
    } else println("isLoading is FALSE")
//    val sendImages = remember { viewModel::sendImagesSequentially }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator() // Show loading indicator
        } else {
            ProductForm(
                product = uiState.product,
                onValueChange = { update -> viewModel.updateProduct { update } },
                onImageAdd = { uri -> viewModel.addImage(uri) },
                onSubmit = { viewModel.submitProduct() },
                onSendImages = {
                    viewModel.setIsLoading(true)
                    viewModel.sendImagesSequentially(context, uiState.imageUris)
                },
                imageUris = uiState.imageUris
            )
        }
    }
}

@Composable
fun ProductForm(
    product: ProductItem,
    onValueChange: (ProductItem) -> Unit, // Change the lambda type
    onImageAdd: (Uri) -> Unit,
    onSubmit: () -> Unit,
    onSendImages: () -> Unit,
    imageUris: List<Uri>
) {

    // Image picker launcher
    val imagePicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { onImageAdd(it) }
        }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = product.title,
            onValueChange = {
                onValueChange(product.copy(title = it)) // Pass updated product item
            },
            label = { Text("Product Name") }
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = product.description,
            onValueChange = {
                onValueChange(product.copy(description = it)) // Pass updated product item
            },
            label = { Text("Description") }
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = product.category,
            onValueChange = {
                onValueChange(product.copy(category = it)) // Pass updated product item
            },
            label = { Text("Category") }
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = product.price.toString(),
            onValueChange = {
                onValueChange(product.copy(price = it.toDouble())) // Pass updated product item
            },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = product.discount.toString(),
            onValueChange = {
                onValueChange(product.copy(discount = it.toDouble())) // Pass updated product item
            },
            label = { Text("Discount (%)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = product.place,
            onValueChange = {
                onValueChange(product.copy(place = it)) // Pass updated product item
            },
            label = { Text("Place") }
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = product.priceUnit,
            onValueChange = {
                onValueChange(product.copy(priceUnit = it)) // Pass updated product item
            },
            label = { Text("Price Unit") }
        )

        Spacer(Modifier.width(16.dp))
        Row {
            Button(onClick = { imagePicker.launch("image/*") }) {
                Text("Select Image")
            }
            Spacer(Modifier.width(40.dp))


            Button(
                onClick = onSendImages,
                enabled = imageUris.isNotEmpty()
            ) {
                Text(text = "AutoFill according to the images")
            }

        }

        // Display selected images
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(imageUris) { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Selected Image",
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = onSubmit, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Submit")
        }

    }
}