package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.BasketProduct
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch


class BasketScreenViewModel : ViewModel() {
    private val _productsInBasket = mutableStateOf<List<BasketProduct>>(sampleProducts())
    val productsInBasket = _productsInBasket


    private val _subtotal = mutableDoubleStateOf(0.0)
    val subtotal = _subtotal


    private val shipping = 5.0


    private val _total = mutableDoubleStateOf(0.0)
    val total = _total

    val db = FirebaseFirestore.getInstance().collection("cartProducts")

    init {
        fetchBasketProducts()
    }


    private fun fetchBasketProducts() {
        db.get()
            .addOnSuccessListener { result ->
                val basketProducts = mutableListOf<BasketProduct>()
                for (document in result) {
                    val product = document.toObject<BasketProduct>()
                    basketProducts.add(product)
                }
                _productsInBasket.value = basketProducts
                calculateTotals()
            }
            .addOnFailureListener { exception ->
                println("Error fetching basket products: $exception")
            }

    }

    fun updateQuantity(productId: String, newQuantity: Int) {
        viewModelScope.launch {
            // Update the local state first
            _productsInBasket.value = _productsInBasket.value.map { product ->
                if (product.id == productId) {
                    product.copy(quantity = newQuantity)
                } else {
                    product
                }
            }
            calculateTotals()
            // Update the quantity field in Firestore document
            db.document(productId)
                .update("quantity", newQuantity)
                .addOnSuccessListener {
                    println("Quantity updated successfully!")
                }
                .addOnFailureListener { e ->
                    println("Error updating quantity: $e")
                }
        }
    }


    fun removeProduct(productId: String) {
        viewModelScope.launch {
            _productsInBasket.value = _productsInBasket.value.filterNot { it.id == productId }
            calculateTotals()
        }
    }


    private fun calculateTotals() {
        _subtotal.doubleValue = _productsInBasket.value.sumOf { it.price * it.quantity }
        _total.doubleValue = _subtotal.doubleValue + shipping
    }

    fun deleteBasketFromFirestore(productID: String?) {
        val document = db.document(productID ?: "")

        document.delete()
            .addOnSuccessListener {
                println("Product successfully deleted!")
                _productsInBasket.value = _productsInBasket.value.filter { it.id != productID }
                calculateTotals()
            }
            .addOnFailureListener { e ->
                println("Error deleting product: $e")
            }
    }

    private fun sampleProducts() = listOf(
        BasketProduct(
            "1",
            "Apple",
            0.99,
            priceUnit = "S",
            2,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJHB2LmJDE8mRo5vCggGcP-G5Jkov0nOYt700GGxzzQg&s"
        ),
        BasketProduct(
            "2",
            "Orange",
            1.29,
            priceUnit = "S",
            3,
            "https://cdn.britannica.com/24/174524-050-A851D3F2/Oranges.jpg"
        )
    )
}
