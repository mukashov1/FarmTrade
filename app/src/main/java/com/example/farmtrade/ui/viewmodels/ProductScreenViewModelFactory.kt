package com.example.farmtrade.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmtrade.data.repository.DataStoreRepository

class ProductScreenViewModelFactory(private val repository: DataStoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}