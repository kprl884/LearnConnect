package com.example.learnconnect.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enrolled_courses")
data class EnrolledCourseEntity(
    @PrimaryKey val courseId: String,
    val userId: String,
    val enrolledDate: Long = System.currentTimeMillis(),
    val progress: Float = 0f
)