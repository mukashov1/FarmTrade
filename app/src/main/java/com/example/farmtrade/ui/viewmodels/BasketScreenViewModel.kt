package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.BasketProduct
import kotlinx.coroutines.launch


class BasketScreenViewModel : ViewModel() {
    private val _productsInBasket = mutableStateOf<List<BasketProduct>>(sampleProducts())
    val productsInBasket = _productsInBasket


    private val _subtotal = mutableDoubleStateOf(0.0)
    val subtotal = _subtotal


    private val shipping = 5.0


    private val _total = mutableDoubleStateOf(0.0)
    val total = _total


    init {
        calculateTotals()
    }


    fun updateQuantity(productId: String, newQuantity: Int) {
        viewModelScope.launch {
            _productsInBasket.value = _productsInBasket.value.map { product ->
                if (product.id == productId) product.copy(quantity = newQuantity) else product
            }
            calculateTotals()
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


    // Sample data generation function can be here or elsewhere
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
