package com.example.learnconnect.data.local

import com.example.learnconnect.data.local.preferences.UserPreferences
import com.example.learnconnect.domain.AuthRepository
import com.example.learnconnect.domain.User
import com.example.learnconnect.utils.Extensions.toDomain
import java.util.UUID

class AuthRepositoryImpl(
    private val userDao: UserDao,
    private val userPreferences: UserPreferences
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Result<User> {
        return try {
            val userEntity = UserEntity(
                id = UUID.randomUUID().toString(),
                email = email,
                password = password
            )
            userDao.insertUser(userEntity)
            val user = userEntity.toDomain()
            userPreferences.saveUser(user)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(email: String, password: String): Result<User> {
        return try {
            val userEntity = userDao.getUserByEmail(email)
                ?: return Result.failure(Exception("User not found"))
            /*
            if (!password.verifyPassword(userEntity.password)) {
                return Result.failure(Exception("Invalid password"))
            }


             */
            val user = userEntity.toDomain()
            userPreferences.saveUser(user)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUser(): User? {
        return userPreferences.getUser()?.let { user ->
            userDao.getUserById(user.id)?.toDomain()
        }
    }
}