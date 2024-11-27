package com.example.learnconnect.ui.video.nav

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.video.VideoScreen
import com.example.learnconnect.ui.video.VideoViewModel

class VideoNavRegisterer : NavRegisterer {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Screen.VideoScreen> {
            val viewModel = hiltViewModel<VideoViewModel>()

            VideoScreen(
                videoId = it.arguments?.getString("videoId") ?: "",
                viewModel = viewModel
            )
        }
    }
}