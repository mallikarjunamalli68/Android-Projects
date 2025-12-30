package com.example.advancedloginandsignup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "users")
class UserEntity(
    @PrimaryKey
    val email: String,
    val password: String)