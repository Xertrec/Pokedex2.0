package com.espartanhack.pokedex20.capturarpokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun CapturarPokemonScreen(
    viewModel: CapturarPokemonViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val teamId by viewModel.teamId.collectAsStateWithLifecycle()
    val zones by viewModel.getZones(teamId!!).collectAsStateWithLifecycle()

    Box(){
        Image(
            painter = painterResource(id = R.drawable.pokedex_fondo), // Cambia al recurso de tu imagen
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn {


            items(
                items = zones,
                key = { zone -> zone.zone.name } // Si tienes un identificador único
            ) { zone ->
                // Muestra el nombre de la zona
                Text(

                    text = zone.zone.name, // Accede al nombre de la zona
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge, // Aplica estilo
                    modifier = Modifier
                        .padding(16.dp) // Margen alrededor del texto
                )
            }
        }


        Box(
            modifier = Modifier
                .padding(start = 18.dp, top = 32.dp) // Ajusta la posición del botón
                .align(Alignment.TopStart) // Posiciona el botón en la parte superior izquierda
        ) {
            Button(
                onClick = { navController.navigateBack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Color opaco negro

                ),
                modifier = Modifier
                    .size(75.dp) // Ajusta el tamaño del botón
                    .clip(CircleShape) // Para hacerlo redondo
            ) {}
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
                    .offset { IntOffset(x=60, y=2100) }
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