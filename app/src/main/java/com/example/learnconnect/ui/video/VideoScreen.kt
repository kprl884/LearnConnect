package com.example.learnconnect.ui.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnconnect.coreui.VideoPlayer

@Composable
fun VideoScreen(
    videoId: String,
    viewModel: VideoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.loadVideo(videoId)
    Column {
        Spacer(modifier = Modifier.height(128.dp))
        uiState.currentVideo?.let {
            VideoPlayer(
                video = it,
                isOfflineMode = uiState.isOfflineMode,
                onProgressUpdate = { position ->
                    viewModel.saveProgress(position)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Switch(
                checked = uiState.isOfflineMode,
                onCheckedChange = { viewModel.toggleOfflineMode() }
            )
            Text(if (uiState.isOfflineMode) "Offline" else "Online")
        }
    }
}