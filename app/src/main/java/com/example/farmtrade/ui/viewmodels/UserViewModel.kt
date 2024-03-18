package com.example.farmtrade.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.AppDatabase
import com.example.farmtrade.data.db.User
import com.example.farmtrade.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    val signedInUser: LiveData<User?> = liveData {
        emit(repository.findSignedInUser())
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    // Используем liveData builder для асинхронного получения пользователя
    val user = liveData {
        val userData = repository.getUser() // Это suspend вызов
        emit(userData)
    }
}