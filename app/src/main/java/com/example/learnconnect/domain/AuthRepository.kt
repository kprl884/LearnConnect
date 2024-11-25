package com.example.learnconnect.domain

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<User>
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun getCurrentUser(): User?
}