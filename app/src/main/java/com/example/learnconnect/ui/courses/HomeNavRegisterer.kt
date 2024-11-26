package com.example.learnconnect.ui.courses

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen

class MyCoursesNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Screen.MyCoursesScreen> {
           MyCoursesScreen()
        }
    }
}