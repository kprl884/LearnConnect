package com.example.learnconnect.data.local.impl

import com.example.learnconnect.data.Extension.toCourse
import com.example.learnconnect.data.Extension.toFavoriteEntity
import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.FavoriteCoursesDao
import com.example.learnconnect.data.local.entity.EnrolledCourseEntity
import com.example.learnconnect.data.local.mock.MockCourseData
import com.example.learnconnect.data.local.preferences.UserPreferences
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.domain.repository.CourseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseRepositoryImpl(
    private val courseDao: CourseDao,
    private val favoriteCoursesDao: FavoriteCoursesDao,
    private val userPreferences: UserPreferences
) : CourseRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            if (courseDao.getAllCourses().isEmpty()) {
                MockCourseData.getMockCourses().forEach { course ->
                    courseDao.insertCourse(course)
                }
            }
        }
    }

    override suspend fun getAllCourses(): Result<List<Course>> {
        return try {
            val courses = courseDao.getAllCourses()
            val currentUserId = userPreferences.getUser()?.email

            val mappedCourses = courses.map { courseEntity ->
                Course(
                    id = courseEntity.id,
                    title = courseEntity.title,
                    description = courseEntity.description,
                    thumbnailUrl = courseEntity.thumbnailUrl,
                    instructorName = courseEntity.instructorName,
                    duration = courseEntity.duration,
                    isEnrolled = currentUserId?.let { userId ->
                        courseDao.isEnrolled(
                            userId,
                            courseEntity.id
                        )
                    } ?: false,
                    progress = currentUserId?.let { userId ->
                        courseDao.getCourseProgress(
                            userId,
                            courseEntity.id
                        )
                    } ?: 0f,
                    category = courseEntity.category
                )
            }
            Result.success(mappedCourses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEnrolledCourses(): Result<List<Course>> {
        return try {
            val userId = userPreferences.getUser()?.email ?: return Result.failure(Exception("User not logged in"))
            val enrolledCourses = courseDao.getEnrolledCourses(userId)

            val mappedCourses = enrolledCourses.map { courseEntity ->
                Course(
                    id = courseEntity.id,
                    title = courseEntity.title,
                    description = courseEntity.description,
                    thumbnailUrl = courseEntity.thumbnailUrl,
                    instructorName = courseEntity.instructorName,
                    duration = courseEntity.duration,
                    isEnrolled = true,
                    progress = courseDao.getCourseProgress(
                        userId,
                        courseEntity.id
                    ),
                    category = courseEntity.category
                )
            }
            Result.success(mappedCourses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun enrollCourse(courseId: String): Result<Boolean> {
        return try {
            val userId = userPreferences.getUser()?.email ?: return Result.failure(Exception("User not logged in"))

            withContext(Dispatchers.IO) {
                val enrolledCourse = EnrolledCourseEntity(
                    courseId = courseId,
                    userId = userId,
                    enrolledDate = System.currentTimeMillis(),
                    progress = 0f
                )

                courseDao.enrollCourse(enrolledCourse)
            }

            withContext(Dispatchers.IO) {
                val courses = courseDao.getAllCourses()
                courseDao.insertCourses(courses)
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCourseDetail(courseId: String): Result<Course> {
        return try {
            val userId = userPreferences.getUser()?.email
            val courseEntity = courseDao.getCourseById(courseId) ?: throw Exception("Course not found")

            val course = Course(
                id = courseEntity.id,
                title = courseEntity.title,
                description = courseEntity.description,
                thumbnailUrl = courseEntity.thumbnailUrl,
                instructorName = courseEntity.instructorName,
                duration = courseEntity.duration,
                isEnrolled = userId?.let {
                    courseDao.isEnrolled(
                        it,
                        courseId
                    )
                } ?: false,
                progress = userId?.let {
                    courseDao.getCourseProgress(
                        it,
                        courseId
                    )
                } ?: 0f,
                category = courseEntity.category
            )
            Result.success(course)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavoriteCourses(): Result<List<Course>> = try {
        val favorites = favoriteCoursesDao.getAllFavorites()
        Result.success(favorites.map { it.toCourse() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addToFavorites(course: Course): Result<Boolean> = try {
        favoriteCoursesDao.addToFavorites(course.toFavoriteEntity())
        Result.success(true)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun removeFromFavorites(courseId: String): Result<Boolean> = try {
        val course = favoriteCoursesDao.getAllFavorites().find { it.courseId == courseId }
        course?.let {
            favoriteCoursesDao.removeFromFavorites(it)
        }
        Result.success(true)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun isFavorite(courseId: String): Result<Boolean> = try {
        Result.success(favoriteCoursesDao.isFavorite(courseId))
    } catch (e: Exception) {
        Result.failure(e)
    }
}