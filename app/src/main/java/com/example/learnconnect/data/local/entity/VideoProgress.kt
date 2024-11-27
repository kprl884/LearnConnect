package com.example.learnconnect.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_progress")
data class VideoProgress(
    @PrimaryKey
    val videoId: String,
    val position: Long,
    val duration: Long,
    val updatedAt: Long = System.currentTimeMillis()
)