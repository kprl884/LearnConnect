package com.example.learnconnect.domain.model

data class CoursePreview(
    val id: String,
    val title: String,
    val instructor: String,
    val thumbnailUrl: String,
    val progress: Float = 0f,
    val isFavorite: Boolean = false
)