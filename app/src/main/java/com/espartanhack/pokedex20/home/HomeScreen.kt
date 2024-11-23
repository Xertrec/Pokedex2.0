package com.espartanhack.pokedex20.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.espartanhack.pokedex20.R // Asegúrate de importar correctamente tu recurso R

@Composable
fun HomeScreen() {
    // Imagen de fondo que ocupa toda la pantalla
    Image(
        painter = painterResource(id = R.drawable.pokedex_princ), // Recurso de la imagen
        contentDescription = "Imagen de fondo",
        contentScale = ContentScale.Crop, // Ajusta la imagen para cubrir el fondo
        modifier = Modifier.fillMaxSize() // La imagen llena toda la pantalla
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
