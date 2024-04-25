package com.example.farmtrade.data.repository

import com.example.farmtrade.data.db.User
import com.example.farmtrade.data.db.room.AppDatabase

class UserRepository(private val db: AppDatabase) {
    val userDao = db.userDao()

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUser(userUID: String): User {
        return userDao.getUserByUID(userUID)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}
