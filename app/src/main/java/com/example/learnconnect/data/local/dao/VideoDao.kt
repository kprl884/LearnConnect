package com.example.learnconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnconnect.data.local.entity.VideoEntity
import com.example.learnconnect.domain.model.CourseProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos WHERE courseId = :courseId")
    fun getVideosForCourse(courseId: String): Flow<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoEntity>)

    @Query("SELECT * FROM videos WHERE id = :videoId")
    suspend fun getVideo(videoId: String): VideoEntity?

    @Query("UPDATE videos SET downloadProgress = :progress WHERE id = :videoId")
    suspend fun updateDownloadProgress(videoId: String, progress: Float)

    @Query("UPDATE videos SET isDownloaded = :isDownloaded WHERE id = :videoId")
    suspend fun updateDownloadStatus(videoId: String, isDownloaded: Boolean)

    @Query("""
        UPDATE videos 
        SET currentPosition = :position, 
            lastWatchedAt = :timestamp 
        WHERE id = :videoId
    """)

    suspend fun updateWatchProgress(
        videoId: String,
        position: Long,
        timestamp: Long = System.currentTimeMillis()
    )

    @Query("""
        SELECT currentPosition 
        FROM videos 
        WHERE id = :videoId
    """)
    suspend fun getWatchPosition(videoId: String): Long?

    @Query("""
        SELECT 
            COUNT(*) as totalVideos,
            COUNT(CASE WHEN currentPosition > 0 THEN 1 END) as startedVideos,
            COUNT(CASE WHEN currentPosition >= duration * 0.9 THEN 1 END) as completedVideos
        FROM videos 
        WHERE courseId = :courseId
    """)
    suspend fun getCourseProgress(courseId: String): CourseProgress
}