package com.example.learnconnect.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Composable
fun LoginScreen() {
    Column {
        Text("Login Screen")
    }
}

@PreviewScreenSizes
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}