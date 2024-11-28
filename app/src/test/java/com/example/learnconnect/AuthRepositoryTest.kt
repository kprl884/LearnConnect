package com.example.learnconnect

import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.UserDao
import com.example.learnconnect.data.local.entity.UserEntity
import com.example.learnconnect.data.local.impl.AuthRepositoryImpl
import com.example.learnconnect.data.local.preferences.UserPreferences
import com.example.learnconnect.domain.User
import com.example.learnconnect.domain.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AuthRepositoryTest {
    private lateinit var authRepository: AuthRepository
    private lateinit var userDao: UserDao
    private lateinit var courseDao: CourseDao
    private lateinit var userPreferences: UserPreferences

    @Before
    fun setup() {
        userDao = mockk()
        userPreferences = mockk()
        courseDao = mockk()
        authRepository = AuthRepositoryImpl(userDao, courseDao, userPreferences)
    }

    @Test
    fun `login with valid credentials returns success`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val user = UserEntity(id = "1", email = email, password = password)

        coEvery { userDao.getUserByEmail(email) } returns user
        coEvery { userPreferences.saveUser(User(id = user.id, email = user.email, password = user.password)) } just runs

        val result = authRepository.signIn(email, password)

        assertTrue(result.isSuccess)
        assertEquals(user, result.getOrNull())
    }

    @Test
    fun `login with invalid email returns failure`() = runTest {
        val email = "invalid@example.com"
        val password = "password123"

        coEvery { userDao.getUserByEmail(email) } returns null

        val result = authRepository.signIn(email, password)

        assertTrue(result.isFailure)
    }
}
