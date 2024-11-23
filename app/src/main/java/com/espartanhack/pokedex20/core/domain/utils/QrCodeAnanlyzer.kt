package com.espartanhack.pokedex20.core.domain.utils

import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

/**
 * Class that analyzes the image to find a QR code and his content.
 *
 * @param onQrCodeScanned The function to call when a QR code is found.
 */
class QrCodeAnalyzer(
    val onQrCodeScanned: (String) -> Unit
): ImageAnalysis.Analyzer {
    private val reader = MultiFormatReader()
        .apply {
            setHints(
                mapOf(
                    DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE),
                )
            )
        }

    /**
     * The supported image formats.
     */
    private val supportedImageFormat = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888,
    )

    /**
     * Function that analyzes the image to find a QR code, and process his content.
     * TODO: Contemplar el analisis en paralelo
     *
     * @param image The image to analyze.
     */
    override fun analyze(image: ImageProxy) {
        if (image.format !in supportedImageFormat) return
        val data = image.planes.first().buffer

        try {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val width = image.width
            val height = image.height

            var result = decodeImage(
                data = data,
                width = width,
                height = height,
                rotationDegrees = rotationDegrees
            )

            result?.let { result ->
                onQrCodeScanned(result.text)
            }
        } catch (_: Exception) {
        } finally {
            image.close()
            data.clear()
        }
    }

    /**
     * Function that decodes the image to find a QR code.
     *
     * @param data The image data.
     * @param width The image width.
     * @param height The image height.
     * @param rotationDegrees The image rotation.
     *
     * @return The result of the decoding.
     */
    private fun decodeImage(
        data: ByteBuffer,
        width: Int,
        height: Int,
        rotationDegrees: Int
    ): Result? {
        val source = PlanarYUVLuminanceSource(
            data.toByteArray(),
            width,
            height,
            0,
            0,
            width,
            height,
            false
        )
        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

        return try {
            reader.decodeWithState(binaryBitmap)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Function that inverts the colors of the image.
     *
     * @param data The image data.
     * @param width The image width.
     * @param height The image height.
     *
     * @return The image data with the colors inverted.
     */
    private fun invertColors(data: ByteBuffer, width: Int, height: Int): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(data.capacity())
        val bytes = data.toByteArray()
        for (i in bytes.indices) {
            buffer.put(i, (255 - bytes[i]).toByte())
        }
        buffer.rewind()
        return buffer
    }

    /**
     * Function that converts a ByteBuffer to a ByteArray.
     *
     * @return The ByteArray.
     */
    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also { get(it) }
    }
}