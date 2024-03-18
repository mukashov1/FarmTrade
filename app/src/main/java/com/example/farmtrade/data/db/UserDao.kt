package com.example.farmtrade.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): User?

    @Query("SELECT * FROM users WHERE isSigned = 1 LIMIT 1")
    suspend fun findSignedInUser(): User?
}


