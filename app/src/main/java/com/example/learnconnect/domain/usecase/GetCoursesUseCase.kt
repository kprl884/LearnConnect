package com.example.learnconnect.domain.usecase

import com.example.learnconnect.domain.repository.CourseRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke() = courseRepository.getAllCourses()
}

class EnrollCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(courseId: String) = courseRepository.enrollCourse(courseId)
}