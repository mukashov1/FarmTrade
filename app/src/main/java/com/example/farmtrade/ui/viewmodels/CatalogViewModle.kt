package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.data.db.SortOption
import com.example.farmtrade.data.db.Tag
import com.example.farmtrade.data.repository.DataStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(private val catalogDataStoreRepository: DataStoreRepository) : ViewModel() {
    private val _originalCatalogItems = mutableStateOf<List<ProductItem>>(emptyList())
    val currentTag = mutableStateOf(Tag.All)
    var sortOption = mutableStateOf(SortOption.Popularity)
    val favoriteProducts = mutableStateOf(setOf<String>())


    private val _catalogItems = MutableStateFlow<List<ProductItem>>(emptyList())
    val catalogItems: StateFlow<List<ProductItem>> = _catalogItems

    private fun fetchCatalogItemsFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val catalogItemsCollection = db.collection("products")

        viewModelScope.launch {
            catalogItemsCollection.get().addOnSuccessListener { documents ->
                val catalogItems = mutableListOf<ProductItem>()
                for (document in documents) {
                    val product = document.toObject<ProductItem>()
                    catalogItems.add(product)
                }
                _catalogItems.value = catalogItems
                _originalCatalogItems.value = catalogItems

                println("SUCCESS")
                println(catalogItems)
            }.addOnFailureListener { exception ->
                // Handle failure
                println("FAILURE $exception")
            }
        }
    }
    fun sortCatalogItems(option: SortOption) {
        sortOption.value = option
        _catalogItems.value = when (option) {
            SortOption.Popularity -> _catalogItems.value.sortedByDescending { it.price }
            SortOption.PriceDescending -> _catalogItems.value.sortedByDescending { it.priceWithDiscount.toDouble() }
            SortOption.PriceAscending -> _catalogItems.value.sortedBy { it.priceWithDiscount.toDouble() }
        }
    }

//    fun filterCatalogItemsByTag(tag: Tag) {
//        currentTag.value = tag
//        val allItems = _originalCatalogItems.value
//        _catalogItems.value = if (tag == Tag.All) {
//            allItems
//        } else {
//            allItems.filter { it.tags.any { itemTag -> itemTag.equals(tag.title, ignoreCase = true) } }
//        }
//    }

    fun toggleFavorite(productId: String) {
        val currentFavorites = favoriteProducts.value.toMutableSet()
        if (currentFavorites.contains(productId)) {
            currentFavorites.remove(productId)
        } else {
            currentFavorites.add(productId)
        }
        favoriteProducts.value = currentFavorites
    }

    fun isProductFavorite(productId: String): Boolean {
        return favoriteProducts.value.contains(productId)
    }

    init {
        fetchCatalogItemsFromFirestore()
    }
}
