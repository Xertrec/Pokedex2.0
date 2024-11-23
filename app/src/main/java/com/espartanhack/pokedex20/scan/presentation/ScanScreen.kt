package com.espartanhack.pokedex20.scan.presentation

import android.content.Context
import android.util.Size
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.espartanhack.pokedex20.R
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenHome
import com.espartanhack.pokedex20.core.utils.QrCodeAnalyzer
import com.espartanhack.pokedex20.pokedex.lightRed
import com.espartanhack.pokedex20.scan.presentation.components.ScanViewModel

@Composable
fun ScanScreen(
    navController: NavController, // Para la navegación al Home
    viewModel: ScanViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val qrData by viewModel.data.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo de la pantalla con el diseño del Pokédex
        AndroidView(
            factory = { context ->
                previewImage(
                    context = context,
                    lifecycleOwner = lifecycleOwner,
                    result = viewModel::processQrData
                )
            },
            modifier = Modifier
                .fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.pokedex_cam_sinfondo), // Cambia por el ID de tu recurso de imagen
            contentDescription = "Pokédex background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón para volver a HomeScreen (parte inferior izquierda)
        Button(
            onClick = { navController.navigate(ScreenHome) }, // Ajusta la ruta al nombre de tu HomeScreen
            colors = ButtonDefaults.buttonColors(
                containerColor = lightRed, // Fondo negro opaco
                contentColor = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.BottomStart) // Ubicación: esquina inferior izquierda
                .offset { IntOffset(x = 50, y = -37) }
                .padding(16.dp) // Margen para separación del borde
                .width(300.dp) // Ajustamos el ancho del botón
                .height(60.dp) // Altura del botó
        ) {
            Text(
                text = "Tornar",
                fontSize = 20.sp // Tamaño del texto ajustado
            )
        }
    }

    // Mostrar un mensaje si se detecta un QR
    LaunchedEffect(qrData) {
        if (qrData != null) {
            Toast.makeText(context, qrData, Toast.LENGTH_SHORT).show()
        }
    }
}


/**
 * Preview the camera image and analyze the Qr code.
 *
 * @param context The context of the screen.
 * @param lifecycleOwner The lifecycle owner of the screen.
 * @param result The result of the Qr code analysis.
 *
 * @return The preview view of the camera.
 */
private fun previewImage(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    result: (String) -> Unit
): PreviewView {
    val cameraProvider = ProcessCameraProvider.getInstance(context)

    val previewView = PreviewView(context)
    val preview = Preview.Builder().build()
    preview.surfaceProvider = previewView.surfaceProvider

    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()
    val resolutionSelector = ResolutionSelector.Builder()
        .setResolutionStrategy(
            ResolutionStrategy(
                Size(previewView.width, previewView.height),
                ResolutionStrategy.FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER
            )
        )
        .build()

    val imageAnalysis = ImageAnalysis.Builder()
        .setResolutionSelector(resolutionSelector)
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    imageAnalysis.setAnalyzer(
        ContextCompat.getMainExecutor(context),
        QrCodeAnalyzer { result(it) }
    )

    try {
        cameraProvider.get().bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageAnalysis
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return previewView
}
