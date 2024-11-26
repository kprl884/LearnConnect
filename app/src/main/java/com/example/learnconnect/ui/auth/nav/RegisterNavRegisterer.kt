package com.example.learnconnect.ui.auth.nav

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.auth.RegisterScreen
import com.example.learnconnect.ui.auth.RegisterViewModel

class RegisterNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Screen.RegisterScreen> {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(viewModel, {
                navController.navigate(Screen.HomeScreen)
            }, {})
        }
    }
}