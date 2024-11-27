package com.example.learnconnect.ui.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.domain.model.CoursePreview

@Composable
fun CourseSection(
    title: String,
    courses: List<CoursePreview>,
    onCourseClick: (String) -> Unit,
    onFavoriteClick: (Course) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = courses,
                key = { it.id }
            ) { course ->
                CourseCard(
                    course = course,
                    onClick = { onCourseClick(course.id) },
                    modifier = Modifier,
                    isFavorite = false,
                    onFavoriteClick = { courses ->
                        onFavoriteClick(courses)
                    }
                )
            }
        }
    }
}