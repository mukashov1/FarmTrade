package com.example.farmtrade.data.repository

import com.example.farmtrade.data.db.User
import com.example.farmtrade.data.db.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun getUser(): User? = userDao.getUser()
    suspend fun findSignedInUser(): User? = userDao.findSignedInUser()
}