package com.example.learnconnect.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnconnect.data.local.dao.VideoProgressDao
import com.example.learnconnect.data.local.entity.VideoProgress
import com.example.learnconnect.data.local.mock.MockVideoData
import com.example.learnconnect.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoProgressDao: VideoProgressDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(VideoUiState())
    val uiState = _uiState.asStateFlow()
    
    private var currentVideoId: String? = null
    
    fun loadVideo(videoId: String) {
        currentVideoId = videoId
        viewModelScope.launch {
            val video = MockVideoData.videos.find { it.id == videoId }
            val progress = videoProgressDao.getProgress(videoId)
            
            _uiState.update { state ->
                state.copy(
                    currentVideo = video,
                    currentPosition = progress?.position ?: 0L
                )
            }
        }
    }
    
    fun saveProgress(position: Long) {
        currentVideoId?.let { videoId ->
            viewModelScope.launch {
                videoProgressDao.saveProgress(
                    VideoProgress(
                        videoId = videoId,
                        position = position,
                        duration = uiState.value.currentVideo?.duration ?: 0L
                    )
                )
            }
        }
    }
    
    fun toggleOfflineMode() {
        _uiState.update { it.copy(isOfflineMode = !it.isOfflineMode) }
    }
}

data class VideoUiState(
    val currentVideo: Video? = null,
    val currentPosition: Long = 0L,
    val isOfflineMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)