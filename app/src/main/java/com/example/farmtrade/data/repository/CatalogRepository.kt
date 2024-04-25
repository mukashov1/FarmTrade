package com.example.farmtrade.data.repository

import com.example.farmtrade.data.db.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class CatalogRepository {
    suspend fun fetchCatalogItems(): Result<List<ProductItem>> {
        return try {
            val snapshot = FirebaseFirestore.getInstance().collection("products").get().await()
            val items = snapshot.documents.mapNotNull {
                it.toObject<ProductItem>()?.copy(id = it.id)
            }
            Result.success(items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}