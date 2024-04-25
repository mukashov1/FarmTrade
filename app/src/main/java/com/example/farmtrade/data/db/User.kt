package com.example.farmtrade.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userUID: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val addressLat: Double = 0.0,
    val addressLng: Double = 0.0,
)