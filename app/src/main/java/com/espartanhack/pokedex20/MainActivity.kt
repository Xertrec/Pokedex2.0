package com.espartanhack.pokedex20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.presentation.navigation.NavGraph
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenHome
import com.espartanhack.pokedex20.core.presentation.theme.Pokedex20Theme
import com.espartanhack.pokedex20.pokedex.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Pokedex20Theme {
                NavGraph(
                    startScreen = ScreenHome::class,
                    navController = rememberNavController()
                )
            }

            LaunchedEffect(Unit) {
                val initialSetup = prefs.getBool(Prefs.INITIAL_SETUP).first()
                if (!initialSetup) {
                }
            }
        }
    }
}