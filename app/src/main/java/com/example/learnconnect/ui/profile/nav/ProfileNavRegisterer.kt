package com.example.learnconnect.ui.profile.nav

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.profile.ProfileScreen
import com.example.learnconnect.ui.profile.ProfileViewModel

class ProfileNavRegisterer : NavRegisterer {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Screen.ProfileScreen> {
            val viewModel = hiltViewModel<ProfileViewModel>()

            ProfileScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onFavoriteClick = { course -> viewModel.toggleFavorite(course) },
                onLogoutClick = { viewModel.logout() },
                onCourseClick = { courseId ->
                    //navController.navigate(Screen.CourseDetailScreen.createRoute(courseId))
                }
            )
        }
    }
}