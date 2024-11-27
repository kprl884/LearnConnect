package com.example.learnconnect.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnconnect.domain.model.Course
import com.example.learnconnect.domain.usecase.EnrollCourseUseCase
import com.example.learnconnect.domain.usecase.GetCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val enrollCourseUseCase: EnrollCourseUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchCourses()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnEnrollCourse -> enrollCourse(event.courseId)
            is HomeUiEvent.OnRefreshCourses -> fetchCourses()
        }
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getCoursesUseCase()
                .onSuccess { courses ->
                    _uiState.update { 
                        it.copy(
                            courses = courses,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { 
                        it.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun enrollCourse(courseId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isEnrolling = true) }
            enrollCourseUseCase(courseId)
                .onSuccess { success ->
                    if (success) {
                        fetchCourses()
                    }
                }
                .onFailure { error ->
                    _uiState.update { 
                        it.copy(
                            error = error.message,
                            isEnrolling = false
                        )
                    }
                }
        }
    }
}

data class HomeUiState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val isEnrolling: Boolean = false,
    val error: String? = null
)

sealed class HomeUiEvent {
    data class OnEnrollCourse(val courseId: String) : HomeUiEvent()
    data object OnRefreshCourses : HomeUiEvent()
}