package com.example.learnconnect.domain.repository

import com.example.learnconnect.data.local.dao.VideoDao
import com.example.learnconnect.domain.model.CourseProgress
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepository @Inject constructor(
    private val videoDao: VideoDao
) {
    suspend fun saveWatchProgress(
        videoId: String,
        position: Long
    ) {
        videoDao.updateWatchProgress(
            videoId,
            position
        )
    }

    suspend fun updateDownloadProgress(
        videoId: String,
        progress: Float
    ) {
        videoDao.updateDownloadProgress(
            videoId,
            progress
        )
        if (progress >= 1f) {
            videoDao.updateDownloadStatus(
                videoId,
                true
            )
        }
    }

    suspend fun getCourseProgress(courseId: String): CourseProgress {
        return videoDao.getCourseProgress(courseId)
    }
}