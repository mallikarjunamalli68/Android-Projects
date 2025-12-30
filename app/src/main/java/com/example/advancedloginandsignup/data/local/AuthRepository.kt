package com.example.advancedloginandsignup.data.local

import com.example.advancedloginandsignup.ui_forgot.ForgotPasswordViewModel

class AuthRepository(private val userDao: UserDao) {
    suspend fun signUp(email: String, password: String): Result<Unit> {
        val existringUser = userDao.getUserByEmail(email)
        return if (existringUser != null){
            Result.failure(Exception("User already exists"))
        } else {
            userDao.insertUser(UserEntity(email, password))
            Result.success(Unit)
        }
    }

    suspend fun login(email: String, password: String): Boolean{
        return userDao.login(email, password) != null
    }
}