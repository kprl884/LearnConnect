package com.example.learnconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.learnconnect.core.navigation.NavProvider
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.ui.theme.LearnConnectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navProvider: NavProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            LearnConnectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    println(innerPadding)
                    NavHost(navController = navController, startDestination = Screen.SplashScreen) {
                        navProvider.screens.forEach { navScreen ->
                            navScreen.screen.registerGraph(navGraphBuilder = this, navController = navController)
                        }
                    }
                }
            }
        }
    }
}