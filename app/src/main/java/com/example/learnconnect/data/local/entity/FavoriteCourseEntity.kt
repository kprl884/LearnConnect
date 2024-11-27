package com.example.learnconnect.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_courses")
data class FavoriteCourseEntity(
    @PrimaryKey
    val courseId: String,
    val title: String,
    val instructor: String,
    val thumbnailUrl: String,
    val addedAt: Long = System.currentTimeMillis()
)