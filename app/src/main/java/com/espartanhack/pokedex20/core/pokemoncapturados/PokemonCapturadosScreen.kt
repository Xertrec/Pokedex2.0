package com.espartanhack.pokedex20.core.pokemoncapturados

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.espartanhack.pokedex20.R
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenHome
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenScan
import com.espartanhack.pokedex20.pokedex.HomeScreen
import com.espartanhack.pokedex20.pokedex.lightRed


@Composable
fun PokemonCapturadosScreen(navController: NavController) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.pokedex_fondo), // Cambia al recurso de tu imagen
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
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

        Text(
            text = "Col·lecció Pokémon",
            color = Color.Black, // Color del texto
            fontSize = 35.sp, // Tamaño del texto
            fontWeight = FontWeight.Bold, // Negrita
            modifier = Modifier
                .align(Alignment.TopStart) // Alineado en la parte superior y centrado
                .offset { IntOffset( x=90, y=370) }
                .padding(top = 16.dp) // Margen desde la parte superior
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre los botones

        // Botón para volver a la pantalla anterior (HomeScreen)
        Button(
            onClick = {
                navController.navigate(ScreenHome) // Navegar hacia atrás
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = lightRed, // Fondo rojo flojo
                contentColor = Color.Black // Color del texto
            ),
            modifier = Modifier
                .width(300.dp) // Ajustamos el ancho del botón
                .height(60.dp) // Altura del botón
                .offset { IntOffset(x=100, y=2200) }
        ) {
            Text(
                text = "Inici",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}