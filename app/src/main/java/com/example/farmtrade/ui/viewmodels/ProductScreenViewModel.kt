package com.example.farmtrade.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.data.repository.DataStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductScreenViewModel(private val repository: DataStoreRepository) : ViewModel() {

    private val _product = MutableStateFlow<ProductItem>(ProductItem())
    val product: StateFlow<ProductItem> = _product

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val db = FirebaseFirestore.getInstance().collection("products")
    fun loadProduct(productId: String) {
        _isLoading.value = true
        println("PRODUCTID IN VIEWMODEL $productId")
        db.document(productId).get()
            .addOnSuccessListener { document ->
                // Check if the query returned any documents
                println("GET BY ID ")
                _product.value = document.toObject<ProductItem>() ?: ProductItem()
                println("SUCCESS")
                println(_product.value)
                _isLoading.value = false
            }
            .addOnFailureListener { exception ->
                // Handle failures
                println("Error getting documents: $exception")
                _isLoading.value = false
            }
    }
}
