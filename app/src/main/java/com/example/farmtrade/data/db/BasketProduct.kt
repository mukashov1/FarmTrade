package com.example.farmtrade.data.db

data class BasketProduct(
    var id: String? = "",
    val title: String = "",
    val price: Double = 0.0,
    val priceUnit: String = "",
    var quantity: Int = 1,
    val imageUrl: String = "",
)
