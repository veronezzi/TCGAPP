package com.seuapp.pokescanner.core.crop

import android.graphics.Bitmap
import android.util.Log

/**
 * Cortador de região inferior esquerda da carta.
 * 
 * Região de crop onde fica o número da carta (ex: 076/091):
 * - X: 0% até 50% da largura (ajustado para pegar número completo)
 * - Y: 75% até 100% da altura (ajustado para pegar região inferior)
 */
class CardBottomLeftCropper {
    companion object {
        private const val TAG = "CardBottomLeftCropper"
        private const val CROP_LEFT = 0.0f
        private const val CROP_RIGHT = 0.50f  // Aumentado de 0.45 para 0.50 para pegar números maiores
        private const val CROP_TOP = 0.75f    // Aumentado de 0.70 para 0.75 para garantir que está na parte inferior
        private const val CROP_BOTTOM = 1.0f
    }

    fun cropBottomLeftRegion(bitmap: Bitmap): Bitmap {
        try {
            val width = bitmap.width
            val height = bitmap.height
            
            if (width <= 0 || height <= 0) {
                Log.w(TAG, "Bitmap inválido: width=$width, height=$height")
                return bitmap
            }
            
            val left = (width * CROP_LEFT).toInt().coerceAtLeast(0)
            val top = (height * CROP_TOP).toInt().coerceAtLeast(0)
            val right = (width * CROP_RIGHT).toInt().coerceAtMost(width)
            val bottom = (height * CROP_BOTTOM).toInt().coerceAtMost(height)
            
            val cropWidth = right - left
            val cropHeight = bottom - top
            
            if (cropWidth <= 0 || cropHeight <= 0) {
                Log.w(TAG, "Dimensões de crop inválidas: width=$cropWidth, height=$cropHeight")
                return bitmap
            }
            
            Log.d(TAG, "Crop: left=$left, top=$top, right=$right, bottom=$bottom (${cropWidth}x${cropHeight})")
            
            return Bitmap.createBitmap(bitmap, left, top, cropWidth, cropHeight)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao fazer crop", e)
            return bitmap
        }
    }
}
