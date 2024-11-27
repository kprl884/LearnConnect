package com.example.learnconnect.data.local.impl

import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.UserDao
import com.example.learnconnect.data.local.entity.UserEntity
import com.example.learnconnect.data.local.mock.MockCourseData
import com.example.learnconnect.data.local.preferences.UserPreferences
import com.example.learnconnect.domain.repository.AuthRepository
import com.example.learnconnect.domain.User
import com.example.learnconnect.utils.Extensions.toDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class AuthRepositoryImpl(
    private val userDao: UserDao,
    private val courseDao: CourseDao,
    private val userPreferences: UserPreferences,
) : AuthRepository {
    init {
        CoroutineScope(Dispatchers.IO).launch {
            if (courseDao.getAllCourses().isEmpty()) {
                MockCourseData.getMockCourses().forEach { course ->
                    courseDao.insertCourse(course)
                }
            }
        }
    }

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

    override suspend fun logout(): Result<Boolean> {
        return try {
            userPreferences.setLoggedIn(false)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}