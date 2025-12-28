package com.seuapp.pokescanner.core.ocr

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * OCR especializado para extrair número da carta do canto inferior esquerdo.
 */
class CardNumberOcr {
    companion object {
        private const val TAG = "CardNumberOcr"
        private val CARD_NUMBER_REGEX = Regex(
            """(\d{1,3})\s*(?:\/\s*(\d{1,3}))?""",
            RegexOption.IGNORE_CASE
        )
    }

    private val textRecognizer = TextRecognition.getClient(
        TextRecognizerOptions.Builder().build()
    )

    /**
     * Extrai o número da carta do bitmap recortado.
     */
    suspend fun extractCardNumber(croppedBitmap: Bitmap): String? {
        return try {
            val image = InputImage.fromBitmap(croppedBitmap, 0)
            val task = textRecognizer.process(image)
            
            val textResult = suspendCancellableCoroutine<com.google.mlkit.vision.text.Text> { continuation ->
                task.addOnSuccessListener { text ->
                    continuation.resume(text)
                }.addOnFailureListener { e ->
                    Log.e(TAG, "Erro ao processar OCR", e)
                    continuation.resumeWithException(e)
                }
            }
            
            val fullText = textResult.text
            if (fullText.isBlank()) {
                Log.d(TAG, "Texto OCR vazio")
                return null
            }
            
            Log.d(TAG, "Texto OCR extraído (raw): $fullText")
            
            // Primeiro limpa o texto (corrige "o" para "0", etc)
            val cleanedText = cleanText(fullText)
            Log.d(TAG, "Texto OCR limpo: $cleanedText")
            
            // Tenta encontrar número no texto limpo
            val cardNumber = extractNumberWithRegex(cleanedText)
            
            if (cardNumber != null) {
                Log.d(TAG, "Número da carta extraído: $cardNumber")
            } else {
                Log.w(TAG, "Número da carta não encontrado no texto: $cleanedText")
            }
            
            cardNumber
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao extrair número da carta", e)
            null
        }
    }

    private fun cleanText(text: String): String {
        // Corrige erros comuns de OCR antes de processar
        var cleaned = text
            // Corrige "o" (letra O) para "0" (zero) quando está em contexto numérico
            .replace(Regex("""(\d|^|\s)[oO](\d)"""), "$10$2")  // o76 -> 076
            .replace(Regex("""(\d)[oO](\d|/|\s|$)"""), "$10$2")  // 7o -> 70
            // Corrige "O" (letra O maiúscula) isolada entre números
            .replace(Regex("""(\d)[O](\d)"""), "$10$2")
        
        // Primeiro tenta encontrar padrão numérico diretamente
        val numberPattern = Regex("""\d{1,3}(?:/\d{1,3})?""")
        val numberMatch = numberPattern.find(cleaned)
        if (numberMatch != null) {
            return numberMatch.value.trim()
        }
        
        // Se não encontrou padrão limpo, limpa o texto removendo caracteres não numéricos
        return cleaned
            .replace("\\s+".toRegex(), " ")
            .replace("[^\\d/\\s]".toRegex(), " ")
            .trim()
    }

    private fun extractNumberWithRegex(text: String): String? {
        // Tenta encontrar o padrão número/total primeiro (mais específico)
        val fullMatch = CARD_NUMBER_REGEX.find(text)
        if (fullMatch != null) {
            val number = fullMatch.groupValues[1]
            val total = fullMatch.groupValues.getOrNull(2)
            
            if (total != null && total.isNotBlank()) {
                // Formato: "25/198" -> mantém como está (sem padding obrigatório)
                return "$number/$total"
            } else {
                // Apenas número: "25" -> mantém como está
                return number
            }
        }
        
        // Se não encontrou, tenta encontrar qualquer sequência de números
        val numberOnlyRegex = Regex("""\d{1,3}""")
        val numberMatch = numberOnlyRegex.find(text)
        if (numberMatch != null) {
            val number = numberMatch.value
            Log.d(TAG, "Extraído apenas número: $number")
            return number
        }
        
        return null
    }

    fun release() {
        textRecognizer.close()
    }
}

