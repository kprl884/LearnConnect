package com.example.learnconnect.core.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object SplashScreen: Screen()
    @Serializable
    data object LoginScreen : Screen()
    @Serializable
    data object RegisterScreen: Screen()
    @Serializable
    data object HomeScreen: Screen()
    @Serializable
    data object MyCoursesScreen: Screen()
    @Serializable
    data object ProfileScreen: Screen()
    @Serializable
    data class VideoScreen(val videoId: String): Screen()
}