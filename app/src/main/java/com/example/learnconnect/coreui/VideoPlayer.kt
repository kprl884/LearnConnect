package com.example.learnconnect.coreui

import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import com.example.learnconnect.domain.model.Video
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    video: Video,
    isOfflineMode: Boolean,
    onProgressUpdate: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    DisposableEffect(video) {
        val mediaItem = MediaItem.fromUri(
            if (isOfflineMode && video.offlineUrl != null) {
                video.offlineUrl
            } else {
                video.onlineUrl
            }
        )

        player.setMediaItem(mediaItem)
        player.prepare()

        onDispose {
            onProgressUpdate(player.currentPosition)
            player.release()
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
            }
        },
        modifier = modifier
    )
}