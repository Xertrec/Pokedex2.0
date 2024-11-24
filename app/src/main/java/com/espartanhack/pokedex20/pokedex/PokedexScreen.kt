package com.espartanhack.pokedex20.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.espartanhack.pokedex20.R
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenPokemonCapturados
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenScan

// Definir el color rojo flojo (si no está definido)
val lightRed = Color(0xFFF8C8C8) // Ajusta el código hexadecimal si es necesario

@Composable
fun PokedexScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize() // Caja principal para toda la pantalla
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.pokedex_pokedex), // Cambia al recurso de tu imagen
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón negro opaco encima del círculo azul
        Box(
            modifier = Modifier
                .padding(start = 18.dp, top = 32.dp) // Ajusta la posición del botón
                .align(Alignment.TopStart) // Posiciona el botón en la parte superior izquierda
        ) {
            Button(
                onClick = { navController.navigate(
                    ScreenScan
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

        // Contenedor de los botones
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, bottom = 30.dp), // Añadir margen izquierdo
            verticalArrangement = Arrangement.Bottom, // Alinea los botones en la parte inferior
            horizontalAlignment = Alignment.Start // Alineación de los botones a la izquierda
        ) {
            // Botón para mostrar la lista de Pokémon capturados
            Button(
                onClick = { navController.navigate(ScreenPokemonCapturados)
                    // Acción al pulsar el botón
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightRed, // Fondo rojo flojo
                    contentColor = Color.Black // Color del texto
                ),
                modifier = Modifier
                    .width(300.dp) // Ajustamos el ancho del botón
                    .height(60.dp) // Altura del botón
            ) {
                Text(
                    text = "Veure Pokémon Capturats",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre los botones

            // Botón para volver a la pantalla anterior (HomeScreen)
            Button(
                onClick = {
                    navController.navigateUp() // Navegar hacia atrás
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightRed, // Fondo rojo flojo
                    contentColor = Color.Black // Color del texto
                ),
                modifier = Modifier
                    .width(300.dp) // Ajustamos el ancho del botón
                    .height(60.dp) // Altura del botón
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
