package com.example.fakestoreapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fakestoreapp.screens.HomeScreenUI
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen ){
        composable<HomeScreen> {
            HomeScreenUI()
        }
    }
}

@Serializable
object HomeScreen