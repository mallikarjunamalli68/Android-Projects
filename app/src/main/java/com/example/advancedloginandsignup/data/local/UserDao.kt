package com.example.advancedloginandsignup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    @Query("Select* From users where email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
    @Query("Select*From users where email = :email And password = :password")
    suspend fun login(email: String, password: String): UserEntity?
}