package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class SavedScreenViewModel() : ViewModel() {
    val savedItems = mutableStateOf<List<ProductItem>>(emptyList())
    val db = FirebaseFirestore.getInstance().collection("products")

    init {
        sampleSavedItems()
    }
    fun sampleSavedItems() {
        val exList = mutableListOf<ProductItem>()
        db.whereLessThan("id", 5).get()
            .addOnSuccessListener { documents ->
                // Check if the query returned any documents
                println("GET BY ID ")
                if (documents != null && !documents.isEmpty) {
                    // Iterate through the documents
                    for (document in documents) {
                        // Get the data of each document
                        val data = document.toObject<ProductItem>()
                        exList.add(data)
                        savedItems.value = exList
                    }
                        println("SUCCESS")
                        println(savedItems.value)
                } else {
                    println("Document not found")
                }
            }
            .addOnFailureListener { exception ->
                // Handle failures
                println("Error getting documents: $exception")
            }
    }
}