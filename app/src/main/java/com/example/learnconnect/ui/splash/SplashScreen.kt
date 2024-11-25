package com.example.learnconnect.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learnconnect.R
import com.example.learnconnect.core.navigation.Screen
import com.example.learnconnect.utils.Constants.splashDelay
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
) {
    Loader()

    LaunchedEffect(key1 = true) {
        delay(splashDelay)
        navController.navigate(Screen.LoginScreen) {
            popUpTo(Screen.SplashScreen) { inclusive = true }
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splashlottie))
    val progress by animateLottieCompositionAsState(composition)
    Box(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            modifier = Modifier.align(Alignment.Center),
            composition = composition,
            progress = { progress },
        )
    }
}