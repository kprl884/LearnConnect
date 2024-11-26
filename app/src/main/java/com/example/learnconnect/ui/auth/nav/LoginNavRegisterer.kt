package com.example.learnconnect.ui.auth.nav

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.auth.AuthViewModel
import com.example.learnconnect.ui.auth.LoginScreen

class LoginNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Screen.LoginScreen> {
            val viewModel = hiltViewModel<AuthViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onAuthSuccess = {
                    navController.navigate(Screen.HomeScreen)
                },
                navigateRegisterScreen = { navController.navigate(Screen.RegisterScreen) }
            )
        }
    }
}