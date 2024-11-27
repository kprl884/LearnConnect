package com.example.learnconnect.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Screen.HomeScreen> {
            HomeScreen(
                navController = navController,
                onNavigateToMyCourses = {
                    navController.navigate(Screen.MyCoursesScreen)
                }
            )
        }
    }
}