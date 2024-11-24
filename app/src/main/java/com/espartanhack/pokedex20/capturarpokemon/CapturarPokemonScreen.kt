package com.espartanhack.pokedex20.capturarpokemon

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.espartanhack.pokedex20.R
import com.espartanhack.pokedex20.core.domain.utils.extensions.navigateBack
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenHome
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenScan
import com.espartanhack.pokedex20.pokedex.lightRed
import kotlinx.coroutines.launch

@Composable
fun CapturarPokemonScreen(
    viewModel: CapturarPokemonViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val teamId by viewModel.teamId.collectAsStateWithLifecycle()
    if (teamId.isNullOrEmpty()) return
    val zones by viewModel.getZones(teamId!!).collectAsStateWithLifecycle()

    val added by viewModel.added.collectAsStateWithLifecycle()
    val failure by viewModel.failure.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokedex_fondo), // Cambia al recurso de tu imagen
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón transparente encima del círculo azul
        Box(
            modifier = Modifier
                .padding(start = 18.dp, top = 32.dp) // Ajusta la posición del botón
                .align(Alignment.TopStart) // Posiciona el botón en la parte superior izquierda
        ) {
            Button(
                onClick = { navController.navigate(ScreenScan
                ) // Navegar hacia atrás
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Color opaco negro

                ),
                modifier = Modifier
                    .size(75.dp) // Ajusta el tamaño del botón
                    .clip(CircleShape) // Para hacerlo redondo
            )

            {

            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 16.dp,
                        top = 196.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.Top
                )
            ) {
                item {
                    Text(
                        text = "Zones:",
                        color = Color.Black,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(
                    items = zones,
                    key = { zone -> zone.name } // Si tienes un identificador único
                ) { zone ->

                    Button(
                        onClick = {
                            scope.launch {
                                viewModel.onClickZona(zone.id)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 8.dp,
                                end = 64.dp
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = zone.name, // Accede al nombre de la zona
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge, // Aplica estilo
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    navController.navigate(ScreenHome) // Navegar hacia atrás
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightRed, // Fondo rojo flojo
                    contentColor = Color.Black // Color del texto
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        bottom = 64.dp,
                        end = 64.dp
                    )
                    .size(
                        width = 300.dp,
                        height = 60.dp
                    )
            ) {
                Text(
                    text = "Inici",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        LaunchedEffect(failure) {
            if (failure != null) {
                // Mostrar mensaje de error
                when (failure) {
                    CapturarPokemonViewModel.Failure.NO_POKEMON_CATCHED -> {
                        Toast.makeText(
                            context,
                            "No se ha capturado ningún pokemon",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.resetErrors()
                    }

                    CapturarPokemonViewModel.Failure.COOLDOWN_NOT_FINISHED -> {
                        Toast.makeText(
                            context,
                            "El tiempo de espera no ha terminado",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.resetErrors()
                    }

                    CapturarPokemonViewModel.Failure.OTHER_FAILURE -> {
                        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                        viewModel.resetErrors()
                    }

                    else -> {}
                }
            }
        }
        LaunchedEffect(added) {
            if (failure != null || !added) return@LaunchedEffect
            Toast.makeText(context, "Pokemon capturado", Toast.LENGTH_SHORT).show()
        }
    }
}