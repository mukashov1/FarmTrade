package com.example.farmtrade.ui.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.data.db.SortOption
import com.example.farmtrade.data.db.Tag
import com.example.farmtrade.data.repository.CatalogRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    private val _originalCatalogItems = mutableStateOf<List<ProductItem>>(emptyList())
    val currentTag = mutableStateOf(Tag.All)
    var sortOption = mutableStateOf(SortOption.Popularity)
    private val favoriteProducts = mutableStateOf(setOf<String>())

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _catalogItems = MutableStateFlow<List<ProductItem>>(emptyList())
    val catalogItems: StateFlow<List<ProductItem>> = _catalogItems

    init {
        fetchCatalogItemsFromFirestore()
    }

    private fun fetchCatalogItemsFromFirestore() {
        viewModelScope.launch {
            catalogRepository.fetchCatalogItems().onSuccess {
                _catalogItems.value = it
            }.onFailure {
                Log.e(TAG, "ERROR IN FETCHING CATALOG")
            }
        }
    }

    fun fetchImageUrlsForProduct(productItem: ProductItem, callback: (List<String>) -> Unit) {
        val storageReference =
            FirebaseStorage.getInstance().reference.child("images/${productItem.id}")
        storageReference.listAll().addOnSuccessListener { listResult ->
            listResult.items.forEach { item ->
                item.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrls = productItem.images.toMutableList()
                    imageUrls.add(uri.toString())
                    callback(imageUrls)
                }
            }
        }.addOnFailureListener {
            // Handle any errors
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
}
