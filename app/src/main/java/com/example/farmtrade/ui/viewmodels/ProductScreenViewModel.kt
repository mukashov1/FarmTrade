package com.example.farmtrade.ui.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.data.repository.DataStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductScreenViewModel(private val repository: DataStoreRepository) : ViewModel() {

    private val _product = MutableStateFlow<ProductItem?>(null)
    val product: StateFlow<ProductItem?> = _product

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val db = FirebaseFirestore.getInstance().collection("products")
    fun loadProduct(productId: Int) {
        viewModelScope.launch {

            _isLoading.value = true
            _product.value = db.whereEqualTo("id", productId).get().result.toObjects<ProductItem>()[0]
            _isLoading.value = false
        }
    }
}
