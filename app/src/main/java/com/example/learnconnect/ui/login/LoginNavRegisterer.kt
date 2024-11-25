package com.example.learnconnect.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.learnconnect.core.navigation.DeepLinkConstants.LOGIN_DEEPLINK
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen

class LoginNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Screen.LoginScreen>(
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = LOGIN_DEEPLINK
                }
            )
        ) {
            LoginScreen()
        }
    }
}