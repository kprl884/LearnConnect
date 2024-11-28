package com.example.learnconnect.domain.model

data class CourseProgress(
    val totalVideos: Int,
    val startedVideos: Int,
    val completedVideos: Int
) {
    val progressPercentage: Float
        get() = if (totalVideos > 0) {
            (completedVideos.toFloat() / totalVideos) * 100
        } else 0f
}