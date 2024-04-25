package com.example.farmtrade.ui.components

import com.example.farmtrade.data.db.BasketProduct
import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

fun toggleBasket(product: ProductItem) {
    val isInBasket = isProductInBasket(product.id ?: "")
    if (isInBasket) {
        println("ALREADY IN BASKET")
    } else {
        val collection = Firebase.firestore.collection("cartProducts")
        val basketProduct = BasketProduct(
            id = product.id,
            title = product.title,
            price = product.price,
            priceUnit = product.priceUnit,
            quantity = 1,
            imageUrl = product.images[0]
        )

        try {
            collection.document(product.id ?: "").set(basketProduct)
                .addOnSuccessListener {
                    println("Product added to basket successfully!")
                }
                .addOnFailureListener { e ->
                    println("Error toggling basket: ${e.message}")
                }
        } catch (e: Exception) {
            println("Error toggling basket: ${e.message}")
        }
    }
}

private fun isProductInBasket(productId: String): Boolean {
    val db = Firebase.firestore
    val document = db.collection("cartProducts").document(productId)
    var result = false

    document.get().addOnCompleteListener { task ->
        result = if (task.isSuccessful) {
            val documentSnapshot = task.result
            documentSnapshot?.exists() ?: false
        } else {
            false // Return false if there's an error
        }
    }
    return result
}