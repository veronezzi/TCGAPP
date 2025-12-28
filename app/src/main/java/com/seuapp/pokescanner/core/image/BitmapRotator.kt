package com.seuapp.pokescanner.core.image

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log

/**
 * Rotaciona bitmap para orientação correta.
 */
object BitmapRotator {
    private const val TAG = "BitmapRotator"

    /**
     * Rotaciona a imagem para a orientação correta.
     * CameraX pode retornar a imagem rotacionada.
     */
    fun rotateIfNeeded(bitmap: Bitmap, rotationDegrees: Int = 0): Bitmap {
        if (rotationDegrees == 0 || rotationDegrees == 360) {
            return bitmap
        }

        return try {
            val matrix = Matrix().apply {
                postRotate(rotationDegrees.toFloat())
            }
            val rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            
            // Recicla o bitmap original se criamos um novo
            if (rotated != bitmap) {
                bitmap.recycle()
            }
            
            Log.d(TAG, "Bitmap rotacionado $rotationDegrees graus: ${rotated.width}x${rotated.height}")
            rotated
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao rotacionar bitmap", e)
            bitmap
        }
    }

    /**
     * Para câmera traseira em modo retrato, pode precisar rotacionar.
     * Verifica as dimensões para determinar se precisa rotacionar.
     */
    fun rotateForPortrait(bitmap: Bitmap): Bitmap {
        // Se a largura é maior que a altura, a imagem está em landscape
        // CameraX geralmente retorna assim, precisamos rotacionar para portrait
        return if (bitmap.width > bitmap.height) {
            rotateIfNeeded(bitmap, 90)
        } else {
            bitmap // Já está em portrait
        }
    }
}

