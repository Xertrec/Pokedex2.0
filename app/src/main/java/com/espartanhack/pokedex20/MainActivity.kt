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
import com.espartanhack.pokedex20.core.presentation.theme.Pokedex20Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val initialSetup by prefs.getBool(Prefs.INITIAL_SETUP)
                .collectAsState(initial = Prefs.INITIAL_SETUP_DEFAULT)

            Pokedex20Theme {
                NavGraph(rememberNavController())
            }

            LaunchedEffect(Unit) {
                if (!initialSetup) {
                }
            }
        }
    }
}