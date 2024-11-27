package com.example.learnconnect.domain.repository

import com.example.learnconnect.domain.model.Course

interface CourseRepository {
    suspend fun getAllCourses(): Result<List<Course>>
    suspend fun getEnrolledCourses(): Result<List<Course>>
    suspend fun enrollCourse(courseId: String): Result<Boolean>
    suspend fun getCourseDetail(courseId: String): Result<Course>
    suspend fun getFavoriteCourses(): Result<List<Course>>
    suspend fun addToFavorites(course: Course): Result<Boolean>
    suspend fun removeFromFavorites(courseId: String): Result<Boolean>
    suspend fun isFavorite(courseId: String): Result<Boolean>
}