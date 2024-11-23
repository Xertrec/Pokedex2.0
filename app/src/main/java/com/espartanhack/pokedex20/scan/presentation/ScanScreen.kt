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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.espartanhack.pokedex20.core.utils.QrCodeAnalyzer
import com.espartanhack.pokedex20.scan.presentation.components.ScanViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ScanScreen(
    viewModel: ScanViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val qrData by viewModel.data.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
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
        }
    }

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
) : PreviewView {
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
        QrCodeAnalyzer { result ->
            result(result)
        }
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