package com.seuapp.pokescanner.core.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.Log

/**
 * Pré-processador de imagem para melhorar a qualidade antes do OCR.
 * 
 * Baseado em práticas de apps similares (TCGplayer, Shiny):
 * - Ajuste de contraste e brilho
 * - Conversão para escala de cinza
 * - Melhoria da nitidez
 */
class ImagePreprocessor {
    companion object {
        private const val TAG = "ImagePreprocessor"
    }

    /**
     * Melhora a qualidade da imagem para OCR.
     */
    fun enhanceForOcr(bitmap: Bitmap): Bitmap {
        return try {
            var processed = bitmap
            
            // 1. Redimensiona se for muito pequena (melhora OCR)
            if (bitmap.width < 300 || bitmap.height < 300) {
                val scale = 300f / bitmap.width.coerceAtMost(bitmap.height)
                val newWidth = (bitmap.width * scale).toInt()
                val newHeight = (bitmap.height * scale).toInt()
                processed = Bitmap.createScaledBitmap(processed, newWidth, newHeight, true)
                Log.d(TAG, "Redimensionado para ${newWidth}x${newHeight}")
            }
            
            // 2. Aplica ajustes de contraste e brilho
            processed = enhanceContrastAndBrightness(processed)
            
            // 3. Converte para escala de cinza (melhora OCR)
            processed = toGrayscale(processed)
            
            Log.d(TAG, "Imagem pré-processada: ${processed.width}x${processed.height}")
            processed
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao pré-processar imagem", e)
            bitmap
        }
    }

    /**
     * Ajusta contraste e brilho da imagem.
     */
    private fun enhanceContrastAndBrightness(bitmap: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(result)
        
        val colorMatrix = ColorMatrix().apply {
            // Aumenta contraste (1.2 = 20% mais contraste)
            setScale(1.2f, 1.2f, 1.2f, 1f)
            // Ajusta brilho ligeiramente
            set(floatArrayOf(
                1f, 0f, 0f, 0f, 10f, // Vermelho
                0f, 1f, 0f, 0f, 10f, // Verde
                0f, 0f, 1f, 0f, 10f, // Azul
                0f, 0f, 0f, 1f, 0f   // Alpha
            ))
        }
        
        val paint = Paint().apply {
            colorFilter = ColorMatrixColorFilter(colorMatrix)
        }
        
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return result
    }

    /**
     * Converte imagem para escala de cinza.
     */
    private fun toGrayscale(bitmap: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(result)
        
        val colorMatrix = ColorMatrix().apply {
            setSaturation(0f) // Remove cor = escala de cinza
        }
        
        val paint = Paint().apply {
            colorFilter = ColorMatrixColorFilter(colorMatrix)
        }
        
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return result
    }
}

