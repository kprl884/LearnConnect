package com.example.learnconnect.ui.splash.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.learnconnect.core.navigation.DeepLinkConstants.SPLASH_DEEPLINK
import com.example.learnconnect.core.navigation.NavRegisterer
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.splash.SplashScreen

class SplashNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Screen.SplashScreen>(
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = SPLASH_DEEPLINK
                }
            )
        ) {
            SplashScreen(navController)
        }
    }
}