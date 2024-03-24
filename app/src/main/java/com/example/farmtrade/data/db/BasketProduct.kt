package com.example.farmtrade.data.db

data class BasketProduct(
    val id: String,
    val title: String,
    val price: Double,
    val priceUnit: String,
    var quantity: Int = 1,
    val imageUrl: String,
)
