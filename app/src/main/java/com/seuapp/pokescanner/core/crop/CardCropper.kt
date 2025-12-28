package com.seuapp.pokescanner.core.crop

import android.graphics.Bitmap
import android.util.Log

/**
 * Cortador de carta completa.
 * 
 * Retorna a carta inteira para extrair tanto nome quanto número.
 */
class CardCropper {
    companion object {
        private const val TAG = "CardCropper"
    }

    /**
     * Detecta e corta a região da carta na imagem.
     * 
     * Por enquanto, retorna a imagem inteira.
     * Em versões futuras, pode implementar detecção automática da carta.
     */
    fun cropCard(fullImage: Bitmap): Bitmap {
        try {
            // Por enquanto, retorna a imagem inteira
            // Em versões futuras, pode adicionar detecção de bordas da carta
            // e fazer crop automático
            Log.d(TAG, "Cortando carta: ${fullImage.width}x${fullImage.height}")
            return fullImage
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao cortar carta", e)
            return fullImage
        }
    }
}

