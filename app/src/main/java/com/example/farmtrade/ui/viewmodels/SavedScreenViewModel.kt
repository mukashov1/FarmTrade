package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.Product
import com.example.farmtrade.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SavedScreenViewModel(dataStoreRepository: DataStoreRepository) : ViewModel() {
    private val _savedItems = mutableStateOf<List<Product>>(emptyList())
    val savedItems = _savedItems

    init {
        sampleSavedItems(dataStoreRepository)
    }
    fun sampleSavedItems(dataStoreRepository: DataStoreRepository): List<Product> {
        var savedItems = emptyList<Product>()
        viewModelScope.launch{
             savedItems = dataStoreRepository.catalogItems.first()
        }
        return if (savedItems.isNotEmpty()) savedItems else emptyList()
    }
}