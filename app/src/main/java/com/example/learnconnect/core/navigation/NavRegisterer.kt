package com.example.learnconnect.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavRegisterer {
    fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController)
}