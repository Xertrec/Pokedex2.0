package com.espartanhack.pokedex20.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import com.espartanhack.pokedex20.R
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenPokedex


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize() // Caja principal para toda la pantalla
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.pokedex_fondo), // Cambia al recurso de tu imagen
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

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
                onClick = {
                    navController.navigate(ScreenPokedex) // Navegar hacia atrás

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
                    text = "Pokédex",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre los botones

            // Botón para volver a la pantalla anterior (HomeScreen)
            Button(
                onClick = {
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
                    text = "Equips",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
