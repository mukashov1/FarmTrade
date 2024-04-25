package com.example.farmtrade.ui.components

import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

fun toggleFavorite(product: ProductItem) {
    val isFavorite = isProductFavorite(productId = product.id ?: "")
    if (isFavorite) {
        println("ALREADY FAVORITE")
    } else {
        val collection = FirebaseFirestore.getInstance().collection("savedProducts")
        try {
            // Add the product to the "savedProducts" collection with the document ID set to the product ID
            collection.document(product.id ?: "").set(product)
            // Optionally, you can also update a field in the product document if you just want to indicate it's saved
            // collection.document(product.productId).update("isSaved", true)
        } catch (e: Exception) {
            // Handle the exception
            println("Error toggling favorite: ${e.message}")
        }
    }
}

private fun isProductFavorite(productId: String): Boolean {
    val db = Firebase.firestore
    val document = db.collection("savedProducts").document(productId)
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