package com.espartanhack.pokedex20.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.espartanhack.pokedex20.pokedex.HomeScreen
import com.espartanhack.pokedex20.pokedex.PokedexScreen
import com.espartanhack.pokedex20.scan.presentation.ScanScreen
import kotlin.reflect.KClass

@Composable
fun NavGraph(
    startScreen: KClass<*>,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        composable<ScreenHome> {
            HomeScreen(navController)
        }

        composable<ScreenScan> {
            ScanScreen(navController)
        }
        composable<ScreenPokedex> {
            PokedexScreen(navController)
        }
    }
}