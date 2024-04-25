package com.example.farmtrade.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.farmtrade.data.db.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE userUID = :userUID")
    suspend fun getUserByUID(userUID: String): User

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>
}
