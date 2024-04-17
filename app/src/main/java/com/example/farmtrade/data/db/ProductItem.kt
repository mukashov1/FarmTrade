package com.example.farmtrade.data.db

data class ProductItem(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val place: String = "",
    val price: Int = 0,
    val discount: String = "",
    val priceWithDiscount: Int = 0,
    val priceUnit: String = ""
)