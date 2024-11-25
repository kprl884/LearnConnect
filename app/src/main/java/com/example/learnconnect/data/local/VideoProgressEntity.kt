package com.example.learnconnect.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_progress")
data class VideoProgressEntity(
    @PrimaryKey val videoId: String,
    val userId: String,
    val progress: Long,
    val timestamp: Long = System.currentTimeMillis()
)