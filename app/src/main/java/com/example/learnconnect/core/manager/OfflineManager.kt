package com.example.learnconnect.core.manager

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.learnconnect.data.local.dao.VideoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineManager @Inject constructor(
    private val context: Context,
    private val videoDao: VideoDao,
    private val workManager: WorkManager
) {
    fun downloadVideo(videoId: String, videoUrl: String) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresStorageNotLow(true)
            .build()

        val downloadWork = OneTimeWorkRequestBuilder<VideoDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(workDataOf(
                "video_id" to videoId,
                "video_url" to videoUrl
            ))
            .build()

        workManager.enqueueUniqueWork(
            "video_download_$videoId",
            ExistingWorkPolicy.REPLACE,
            downloadWork
        )
    }

    fun observeDownloadProgress(videoId: String): Flow<Float> = flow {
        videoDao.getVideo(videoId)?.let { video ->
            emit(video.downloadProgress)
        }
    }

    fun cancelDownload(videoId: String) {
        workManager.cancelUniqueWork("video_download_$videoId")
    }
}