package com.espartanhack.pokedex20.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    NavHost(
        navController = rememberNavController(),
        startDestination = ScreenInitial
    ) {
        composable<ScreenInitial> {
        }

        composable<ScreenScan> {
        }
    }
}