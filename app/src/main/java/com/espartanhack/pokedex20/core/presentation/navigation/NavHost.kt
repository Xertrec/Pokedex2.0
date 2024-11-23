package com.espartanhack.pokedex20.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.espartanhack.pokedex20.home.HomeScreen
import com.espartanhack.pokedex20.scan.presentation.ScanScreen

@Composable
fun NavGraph() {
    NavHost(
        navController = rememberNavController(),
        startDestination = ScreenScan
    ) {
        composable<ScreenHome> {
            HomeScreen()
        }

        composable<ScreenScan> {
            ScanScreen()
        }
    }
}