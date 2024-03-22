package com.example.farmtrade.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmtrade.data.repository.CatalogDataStoreRepository

class CatalogViewModelFactory(private val repository: CatalogDataStoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
