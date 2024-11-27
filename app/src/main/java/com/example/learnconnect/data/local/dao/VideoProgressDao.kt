package com.example.learnconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnconnect.data.local.entity.VideoProgress

@Dao
interface VideoProgressDao {
    @Query("SELECT * FROM video_progress WHERE videoId = :videoId")
    suspend fun getProgress(videoId: String): VideoProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: VideoProgress)
}