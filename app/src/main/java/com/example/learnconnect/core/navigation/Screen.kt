package com.example.learnconnect.core.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object SplashScreen: Screen()
    @Serializable
    data object LoginScreen : Screen()
}