package com.example.farmtrade.data.db

data class ProductItem(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val place: String = "",
    val price: Double = 0.0,
    val discount: Double = 0.0,
    val priceWithDiscount: Double = 0.0,
    val priceUnit: String = "",
    val images: List<String> = emptyList()
)