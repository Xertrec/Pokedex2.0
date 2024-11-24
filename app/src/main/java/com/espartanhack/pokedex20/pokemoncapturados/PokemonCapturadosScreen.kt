package com.espartanhack.pokedex20.pokemoncapturados

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.espartanhack.pokedex20.R
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenHome
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenScan
import com.espartanhack.pokedex20.pokedex.lightRed


@Composable
fun PokemonCapturadosScreen(
    viewModel: PokemonCapturadosViewModel = hiltViewModel(),
    navController: NavController
) {
    val teamId by viewModel.teamId.collectAsStateWithLifecycle()
    if (teamId == null) return
    val pokemonCapturados by viewModel.pokemonCapturados(teamId!!).collectAsStateWithLifecycle()

    Box {
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
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 16.dp,
                        top = 196.dp
                    )
            ) {
                Text(
                    text = "Col·lecció",
                    color = Color.Black,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text(
                                text = "Pokémon",
                                color = Color.Black,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        items(pokemonCapturados, key = { it.id }) { pokemon ->
                            Text(
                                text = pokemon.name,
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyLarge, // Aplica estilo
                                modifier = Modifier
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text(
                                text = "Índex",
                                color = Color.Black,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }


                        items(pokemonCapturados, key = { it.id }) { pokemon ->
                            Text(
                                text = pokemon.id.toString(),
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyLarge, // Aplica estilo
                                modifier = Modifier
                            )
                        }
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
    }

}