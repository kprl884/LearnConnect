package com.example.learnconnect.domain.repository

import com.example.learnconnect.domain.User

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<User>
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun getCurrentUser(): User?
    suspend fun logout(): Result<Boolean>

}