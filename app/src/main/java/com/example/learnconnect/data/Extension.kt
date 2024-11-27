package com.example.learnconnect.data

import com.example.learnconnect.data.local.entity.FavoriteCourseEntity
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.domain.model.CoursePreview

object Extension {
    fun Course.toFavoriteEntity() = FavoriteCourseEntity(
        courseId = id,
        title = title,
        instructor = instructorName,
        thumbnailUrl = thumbnailUrl
    )

    fun FavoriteCourseEntity.toCourse() = Course(
        id = courseId,
        title = title,
        instructorName = instructor,
        thumbnailUrl = thumbnailUrl,
        description = "",
        duration = "",
        isEnrolled = true,
        progress = 0f
    )

    // Extension functions
    fun Course.toCoursePreview() = CoursePreview(
        id = id,
        title = title,
        instructor = instructorName,
        thumbnailUrl = thumbnailUrl,
        progress = progress,
        isFavorite = false  // Bu değer repository'den kontrol edilebilir
    )

    fun List<Course>.toCoursePreviewList() = map { it.toCoursePreview() }
}