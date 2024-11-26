package com.example.learnconnect.domain.model

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val instructorName: String,
    val duration: String,
    val isEnrolled: Boolean = false,
    val progress: Float = 0f
)