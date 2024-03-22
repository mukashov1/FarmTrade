package com.example.farmtrade.ui.screens
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmtrade.data.repository.CatalogDataStoreRepository
import com.example.farmtrade.ui.viewmodels.ProductScreenViewModel
import com.example.farmtrade.ui.viewmodels.ProductScreenViewModelFactory

@Composable
fun ProductScreen(productId: String, viewModel: ProductScreenViewModel = viewModel()) {
    val context = LocalContext.current
    val repository = remember { CatalogDataStoreRepository(context) }
    val productViewModel: ProductScreenViewModel = viewModel(
        factory = ProductScreenViewModelFactory(repository)
    )

    LaunchedEffect(productId) {
        productViewModel.loadProduct(productId)
    }

    val isLoading by productViewModel.isLoading.collectAsState()
    val product by productViewModel.product.collectAsState()

    if (isLoading) {
        CircularProgressIndicator() // Show a loading indicator while loading
    } else if (product != null) {
        Text(text = "Product Title: ${product!!.title}")
        // Add more UI elements to display product details as needed
    } else {
        Text(text = "Product not found")
    }
}