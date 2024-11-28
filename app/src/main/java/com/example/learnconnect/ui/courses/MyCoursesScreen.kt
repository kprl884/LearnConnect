package com.example.learnconnect.ui.courses

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.learnconnect.ui.profile.ProfileUiState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.ui.profile.component.CourseSection
import com.example.learnconnect.ui.profile.component.ProfileHeader

@Composable
fun MyCoursesScreen(
    uiState: ProfileUiState,
    onCourseClick: (String) -> Unit,
    onFavoriteClick: (Course) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader(
            userName = uiState.userName,
            email = uiState.email
        )

        CourseSection(
            title = "Kayıtlı Kurslarım",
            courses = uiState.enrolledCourses,
            onCourseClick = onCourseClick,
            onFavoriteClick = onFavoriteClick,
            isFavorite = { courseId ->
                uiState.favoriteCourses.any { it.id == courseId }
            },
            modifier = Modifier
        )
    }
}
