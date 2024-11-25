package com.example.learnconnect.ui.auth.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen

class RegisterNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Screen.RegisterScreen> {
            RegisterScreen()
        }
    }
}