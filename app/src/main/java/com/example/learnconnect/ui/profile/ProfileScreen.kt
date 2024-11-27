package com.example.learnconnect.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.ui.profile.component.CourseSection
import com.example.learnconnect.ui.profile.component.LogoutButton
import com.example.learnconnect.ui.profile.component.ProfileHeader

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onCourseClick: (String) -> Unit,
    onLogoutClick: () -> Unit,
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
            title = "Favori Kurslarım",
            courses = uiState.favoriteCourses,
            onCourseClick = onCourseClick,
            onFavoriteClick = onFavoriteClick,
            modifier = Modifier
        )

        LogoutButton(onClick = onLogoutClick)
    }
}

@PreviewScreenSizes
@Composable
fun ProfileScreenPreview() {
    // ProfileScreen()
}

