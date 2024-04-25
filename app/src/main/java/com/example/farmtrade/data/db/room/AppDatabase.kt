package com.example.farmtrade.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.farmtrade.data.db.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
