package com.example.farmtrade.ui.viewmodels

import androidx.lifecycle.ViewModelProvider
import com.example.farmtrade.data.repository.DataStoreRepository

class BasketScreenViewModelFactory(private val repository: DataStoreRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return BasketScreenViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
}