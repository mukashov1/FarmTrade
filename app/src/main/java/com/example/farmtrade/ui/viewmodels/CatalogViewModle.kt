package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.api.CatalogApiService
import com.example.farmtrade.data.db.Product
import com.example.farmtrade.data.db.SortOption
import com.example.farmtrade.data.db.Tag
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {
    private val _originalCatalogItems = mutableStateOf<List<Product>>(emptyList())
    private val _catalogItems = mutableStateOf<List<Product>>(emptyList())
    val catalogItems = _catalogItems
    var currentTag = mutableStateOf(Tag.All)
    var sortOption = mutableStateOf(SortOption.Popularity)
    val favoriteProducts = mutableStateOf(setOf<String>())

    fun sortCatalogItems(option: SortOption) {
        sortOption.value = option
        _catalogItems.value = when (option) {
            SortOption.Popularity -> _catalogItems.value.sortedByDescending { it.feedback.rating }
            SortOption.PriceDescending -> _catalogItems.value.sortedByDescending { it.price.priceWithDiscount.toDouble() }
            SortOption.PriceAscending -> _catalogItems.value.sortedBy { it.price.priceWithDiscount.toDouble() }
        }
    }

    fun filterCatalogItemsByTag(tag: Tag) {
        currentTag.value = tag
        val allItems = _originalCatalogItems.value
        _catalogItems.value = if (tag == Tag.All) {
            allItems
        } else {
            allItems.filter { it.tags.contains(tag.name.lowercase()) }
        }
    }

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
        loadCatalogItems()
    }

    private fun loadCatalogItems() {
        viewModelScope.launch {
            try {
                val response = CatalogApiService.RetrofitInstance.api.getCatalogItems()
                if (response.isSuccessful) {
                    _originalCatalogItems.value = response.body()?.items ?: listOf()
                    _catalogItems.value = _originalCatalogItems.value
                } else {
                    println("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Error loading catalog items: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
