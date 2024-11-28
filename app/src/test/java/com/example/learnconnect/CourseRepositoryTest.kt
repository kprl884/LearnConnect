package com.example.learnconnect

import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.FavoriteCoursesDao
import com.example.learnconnect.data.local.entity.FavoriteCourseEntity
import com.example.learnconnect.data.local.impl.CourseRepositoryImpl
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.domain.repository.CourseRepository
import com.example.learnconnect.data.local.preferences.UserPreferences
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CourseRepositoryTest {
    private lateinit var courseRepository: CourseRepository
    private lateinit var courseDao: CourseDao
    private lateinit var userPreferences: UserPreferences
    private lateinit var favoriteCoursesDao: FavoriteCoursesDao

    @Before
    fun setup() {
        courseDao = mockk(relaxed = true)
        userPreferences = mockk(relaxed = true)
        favoriteCoursesDao = mockk(relaxed = true)
        courseRepository = CourseRepositoryImpl(
            courseDao,
            favoriteCoursesDao,
            userPreferences
        )
    }

    @Test
    fun `isFavorite should return true when course is in favorites`() = runTest {
        // Given
        val courseId = "1"
        coEvery { favoriteCoursesDao.isFavorite(courseId) } returns true

        // When
        val result = courseRepository.isFavorite(courseId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(
            true,
            result.getOrNull()
        )
    }

    @Test
    fun `addToFavorites should successfully add course to favorites`() = runTest {
        val course = Course(
            id = "1",
            title = "Kotlin Course",
            description = "Learn Kotlin",
            instructorName = "John Doe",
            category = "Programming",
            thumbnailUrl = "",
            duration = "2h",
            isEnrolled = false,
            progress = 0f
        )
        coEvery { favoriteCoursesDao.addToFavorites(any()) } returns Unit

        val result = courseRepository.addToFavorites(course)

        assertTrue(result.isSuccess)
        coVerify {
            favoriteCoursesDao.addToFavorites(
                FavoriteCourseEntity(
                    course.id,
                    course.title,
                    course.description,
                    course.instructorName,
                    course.category
                )
            )
        }
    }
}