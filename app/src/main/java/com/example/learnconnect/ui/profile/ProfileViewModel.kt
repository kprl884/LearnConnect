package com.example.learnconnect.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnconnect.data.Extension.toCoursePreviewList
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.domain.model.CoursePreview
import com.example.learnconnect.domain.repository.AuthRepository
import com.example.learnconnect.domain.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                val enrolledCoursesResult = courseRepository.getEnrolledCourses()
                val favoriteCoursesResult = courseRepository.getFavoriteCourses()

                _uiState.update { state ->
                    state.copy(
                        enrolledCourses = enrolledCoursesResult.getOrDefault(emptyList()).toCoursePreviewList(),
                        favoriteCourses = favoriteCoursesResult.getOrDefault(emptyList()).toCoursePreviewList(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                authRepository.logout()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            try {
                val isFavorite = courseRepository.isFavorite(course.id).getOrDefault(false)
                if (isFavorite) {
                    courseRepository.removeFromFavorites(course.id)
                } else {
                    courseRepository.addToFavorites(course)
                }
                loadProfile()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}

data class ProfileUiState(
    val userName: String = "",
    val email: String = "",
    val enrolledCourses: List<CoursePreview> = emptyList(),
    val favoriteCourses: List<CoursePreview> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)