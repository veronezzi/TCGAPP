package com.seuapp.pokescanner.core.camera

import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.ByteArrayOutputStream
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * Provedor de câmera usando CameraX.
 */
class CameraXProvider(
    private val context: android.content.Context,
    private val lifecycleOwner: LifecycleOwner,
    private val executor: Executor
) {
    private var cameraProvider: ProcessCameraProvider? = null
    private var imageCapture: ImageCapture? = null
    private var preview: Preview? = null

    suspend fun initialize(previewView: PreviewView) {
        cameraProvider = getCameraProvider()
        
        preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
        
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()
        
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        try {
            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        } catch (e: Exception) {
            throw RuntimeException("Erro ao inicializar câmera", e)
        }
    }

    suspend fun captureFrame(): Bitmap? {
        val imageCapture = this.imageCapture ?: return null
        
        return suspendCancellableCoroutine { continuation ->
            imageCapture.takePicture(
                executor,
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        try {
                            val bitmap = imageProxyToBitmap(image)
                            continuation.resume(bitmap)
                        } catch (e: Exception) {
                            android.util.Log.e("CameraXProvider", "Erro ao converter ImageProxy para Bitmap", e)
                            continuation.resume(null)
                        } finally {
                            image.close()
                        }
                    }
                    
                    override fun onError(exception: ImageCaptureException) {
                        android.util.Log.e("CameraXProvider", "Erro ao capturar frame", exception)
                        continuation.resume(null)
                    }
                }
            )
        }
    }

    private fun imageProxyToBitmap(imageProxy: ImageProxy): Bitmap {
        val format = imageProxy.format
        val planes = imageProxy.planes
        
        when (format) {
            ImageFormat.YUV_420_888 -> {
                val yBuffer = planes[0].buffer
                val uBuffer = planes[1].buffer
                val vBuffer = planes[2].buffer
                
                val ySize = yBuffer.remaining()
                val uSize = uBuffer.remaining()
                val vSize = vBuffer.remaining()
                
                val nv21 = ByteArray(ySize + uSize + vSize)
                yBuffer.get(nv21, 0, ySize)
                vBuffer.get(nv21, ySize, vSize)
                uBuffer.get(nv21, ySize + vSize, uSize)
                
                val yuvImage = YuvImage(nv21, ImageFormat.NV21, imageProxy.width, imageProxy.height, null)
                val out = ByteArrayOutputStream()
                yuvImage.compressToJpeg(Rect(0, 0, imageProxy.width, imageProxy.height), 90, out)
                val imageBytes = out.toByteArray()
                return android.graphics.BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            }
            else -> {
                val buffer = planes[0].buffer
                val bytes = ByteArray(buffer.remaining())
                buffer.get(bytes)
                return android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            }
        }
    }

    private suspend fun getCameraProvider(): ProcessCameraProvider {
        return suspendCancellableCoroutine { continuation ->
            ProcessCameraProvider.getInstance(context).also { future ->
                future.addListener(
                    {
                        try {
                            continuation.resume(future.get())
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    },
                    ContextCompat.getMainExecutor(context)
                )
            }
        }
    }

    fun release() {
        cameraProvider?.unbindAll()
        cameraProvider = null
        imageCapture = null
        preview = null
    }
}
