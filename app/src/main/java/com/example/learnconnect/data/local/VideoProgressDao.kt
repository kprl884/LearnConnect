package com.example.learnconnect.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: VideoProgressEntity)

    @Query("SELECT * FROM video_progress WHERE videoId = :videoId AND userId = :userId")
    suspend fun getProgress(videoId: String, userId: String): VideoProgressEntity?
}