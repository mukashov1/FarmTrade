package com.example.farmtrade.data.db

data class Product(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: Price,
    val feedback: Feedback,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<InfoItem>,
    val ingredients: String,
    var isFavorite: Boolean = false
)

data class Price(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)

data class Feedback(
    val count: Int,
    val rating: Double
)

data class InfoItem(
    val title: String,
    val value: String
)
