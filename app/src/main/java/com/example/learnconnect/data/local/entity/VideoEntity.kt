package com.example.learnconnect.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey
    val id: String,
    val courseId: String,
    val title: String,
    val url: String,
    val duration: Long = 0,
    val currentPosition: Long = 0,
    val downloadProgress: Float = 0f,
    val isDownloaded: Boolean = false,
    val lastWatchedAt: Long = System.currentTimeMillis()
)