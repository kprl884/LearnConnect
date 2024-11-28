package com.example.learnconnect.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.learnconnect.R
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.home.component.CourseCard
import com.example.learnconnect.ui.home.component.SearchBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToMyCourses: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        TopBar(
            onMyCoursesClick = onNavigateToMyCourses
        )

        SearchBar(
            searchQuery = uiState.searchQuery,
            onSearchQueryChange = { query ->
                viewModel.onEvent(HomeUiEvent.OnSearchQueryChange(query))
            }
        )
        val filteredCourses = uiState.courses.filter { course ->
            course.title.contains(
                uiState.searchQuery,
                ignoreCase = true
            ) ||
                    course.category.contains(
                        uiState.searchQuery,
                        ignoreCase = true
                    )
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredCourses) { course ->
                CourseCard(
                    course = course,
                    onVideoClick = { courseId ->
                        navController.navigate(Screen.VideoScreen(courseId))
                    },
                    onEnrollClick = {
                        viewModel.onEvent(HomeUiEvent.OnEnrollCourse(course.id))
                    }
                )
            }
        }
    }
}

@Composable
fun TopBar(
    onMyCoursesClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.t_m_kurslar),
            style = MaterialTheme.typography.headlineMedium
        )

        TextButton(onClick = onMyCoursesClick) {
            Text(stringResource(R.string.kay_tl_kurslar_mn))
        }
    }
}