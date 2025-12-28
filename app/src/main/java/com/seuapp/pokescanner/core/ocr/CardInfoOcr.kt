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
 * Resultado do OCR contendo nome e número da carta.
 */
data class CardInfo(
    val name: String?,
    val number: String?
)

/**
 * OCR especializado para extrair nome e número da carta Pokémon.
 * 
 * Estratégia:
 * - Nome: Geralmente na parte superior da carta, em letras grandes
 * - Número: Parte inferior esquerda (ex: 076/091)
 */
class CardInfoOcr {
    companion object {
        private const val TAG = "CardInfoOcr"
        private val CARD_NUMBER_REGEX = Regex(
            """(\d{1,3})\s*(?:\/\s*(\d{1,3}))?""",
            RegexOption.IGNORE_CASE
        )
    }

    private val textRecognizer = TextRecognition.getClient(
        TextRecognizerOptions.Builder().build()
    )

    /**
     * Extrai nome e número da carta do bitmap completo da carta.
     */
    suspend fun extractCardInfo(fullCardBitmap: Bitmap): CardInfo? {
        return try {
            val image = InputImage.fromBitmap(fullCardBitmap, 0)
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
            
            // Extrai número
            val number = extractCardNumber(fullText)
            
            // Extrai nome (primeira linha significativa que não é número)
            val name = extractCardName(fullText, number)
            
            if (name != null || number != null) {
                Log.d(TAG, "CardInfo extraído - Nome: $name, Número: $number")
                return CardInfo(name = name, number = number)
            }
            
            null
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao extrair informações da carta", e)
            null
        }
    }

    /**
     * Extrai o número da carta do texto OCR.
     */
    private fun extractCardNumber(text: String): String? {
        // Primeiro limpa e corrige erros comuns
        val cleaned = cleanTextForNumber(text)
        
        // Tenta encontrar padrão número/total primeiro
        val fullMatch = CARD_NUMBER_REGEX.find(cleaned)
        if (fullMatch != null) {
            val number = fullMatch.groupValues[1]
            val total = fullMatch.groupValues.getOrNull(2)
            
            if (total != null && total.isNotBlank()) {
                return "$number/$total"
            } else {
                return number
            }
        }
        
        // Se não encontrou, tenta encontrar qualquer sequência de números
        val numberOnlyRegex = Regex("""\d{1,3}""")
        val numberMatch = numberOnlyRegex.find(cleaned)
        return numberMatch?.value
    }

    /**
     * Extrai o nome da carta do texto OCR.
     * 
     * Estratégia baseada na estrutura comum de cartas Pokémon:
     * - Linha 1: Tipo de carta (ex: "Estádio", "Pokémon", "Treinador")
     * - Linha 2: NOME DA CARTA (o que queremos!)
     * - Linha 3+: Tipo detalhado (ex: "TREINADOR", "Pokémon Básico") ou descrição
     */
    private fun extractCardName(text: String, detectedNumber: String?): String? {
        val lines = text.lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .filter { 
                // Remove linhas que são apenas números
                !it.matches(Regex("""^\d+(\/\d+)?$"""))
            }
            .filter {
                // Remove linhas muito curtas (provavelmente ruído)
                it.length >= 3
            }
        
        // Palavras-chave que indicam tipo de carta (devem ser ignoradas)
        val cardTypeKeywords = listOf(
            "estádio", "estadio", "stadium",
            "treinador", "trainer", 
            "pokémon", "pokemon",
            "basic", "básico", "básico",
            "stage", "estágio", "estagio",
            "evolution", "evolução", "evolucao",
            "gx", "ex", "v", "vmax", "vstar", "v-union"
        )
        
        // Palavras-chave de texto descritivo (não são o nome)
        val descriptiveKeywords = listOf(
            "damage", "dano", "energy", "energia", "hp", "pv",
            "ability", "habilidade", "attack", "ataque",
            "uma vez", "durante", "pode", "poderá",
            "jogador", "baralho", "banco", "campo"
        )
        
        // Procura nas primeiras 5-8 linhas (onde geralmente está o nome)
        val searchRange = (0 until lines.size.coerceAtMost(8))
        
        for (i in searchRange) {
            val line = lines[i]
            val lowerLine = line.lowercase()
            
            // Pula linhas que contêm o número detectado
            if (detectedNumber != null && line.contains(detectedNumber)) {
                continue
            }
            
            // Pula linhas que são apenas números ou caracteres especiais
            if (line.matches(Regex("""^[\d\s\/\-\.]+$"""))) {
                continue
            }
            
            // Pula linhas que são tipo de carta (ex: "Estádio", "TREINADOR")
            if (cardTypeKeywords.any { keyword -> 
                lowerLine == keyword || lowerLine.startsWith(keyword + " ") || lowerLine == keyword.uppercase()
            }) {
                Log.d(TAG, "Ignorando linha (tipo de carta): $line")
                continue
            }
            
            // Pula linhas que contêm palavras descritivas (são descrições, não nome)
            if (descriptiveKeywords.any { keyword -> lowerLine.contains(keyword) }) {
                Log.d(TAG, "Ignorando linha (contém palavras descritivas): $line")
                continue
            }
            
            // Pula linhas muito longas (provavelmente são descrições)
            if (line.length > 40) {
                continue
            }
            
            // Se a linha anterior era um tipo de carta, esta provavelmente é o nome!
            // Ou se está nas primeiras linhas e não contém palavras-chave, pode ser o nome
            val isAfterCardType = i > 0 && cardTypeKeywords.any { keyword ->
                lines[i - 1].lowercase().let { prevLine ->
                    prevLine == keyword || prevLine.startsWith(keyword + " ") || prevLine == keyword.uppercase()
                }
            }
            
            // Para nomes (geralmente curtos), pega a linha completa, não corta
            // Se a linha for curta (< 30 chars), provavelmente é o nome completo
            val lineToProcess = if (line.length <= 30) {
                // Linha curta = provavelmente é o nome completo, pega tudo
                line.trim()
            } else {
                // Linha longa = pode ter texto extra, pega apenas parte esquerda (80%)
                val lineLength = line.length
                line.substring(0, (lineLength * 0.8).toInt().coerceAtMost(lineLength)).trim()
            }
            
            // Remove caracteres especiais do final apenas, mantém letras e espaços
            // Não remove acentuação (artazón -> artazon se necessário, mas melhor manter)
            val cleanedName = lineToProcess
                .replace(Regex("""[^\w\sáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ]"""), "") // Mantém letras, espaços e acentos
                .trim()
                .replace(Regex("""\s+"""), " ") // Normaliza espaços múltiplos
            
            // O nome geralmente tem entre 3 e 25 caracteres
            if (cleanedName.isNotBlank() && cleanedName.length in 3..25) {
                // Capitaliza primeira letra de cada palavra
                val capitalized = cleanedName.split(" ")
                    .joinToString(" ") { word ->
                        word.lowercase().replaceFirstChar { 
                            if (it.isLowerCase()) it.titlecase() else it.toString() 
                        }
                    }
                
                // Se está após um tipo de carta OU está nas primeiras 3 linhas, é provavelmente o nome
                if (isAfterCardType || i < 3) {
                    Log.d(TAG, "Nome extraído da linha ${i + 1}: $capitalized (após tipo de carta: $isAfterCardType)")
                    return capitalized
                }
            }
        }
        
        // Fallback: se não encontrou com a estratégia acima, tenta nas primeiras linhas
        for (i in 0 until lines.size.coerceAtMost(5)) {
            val line = lines[i]
            val lowerLine = line.lowercase()
            
            // Pula se contém palavras-chave
            if (cardTypeKeywords.any { lowerLine.contains(it) } || 
                descriptiveKeywords.any { lowerLine.contains(it) }) {
                continue
            }
            
            // Pula se contém o número
            if (detectedNumber != null && line.contains(detectedNumber)) {
                continue
            }
            
            // Para fallback também, pega linha completa se for curta
            val lineToProcess = if (line.length <= 30) {
                line.trim()
            } else {
                line.substring(0, (line.length * 0.8).toInt().coerceAtMost(line.length)).trim()
            }
            
            val cleanedName = lineToProcess
                .replace(Regex("""[^\w\sáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ]"""), "") // Mantém acentos
                .trim()
                .replace(Regex("""\s+"""), " ")
            
            if (cleanedName.length in 3..25) {
                val capitalized = cleanedName.split(" ")
                    .joinToString(" ") { word ->
                        word.lowercase().replaceFirstChar { 
                            if (it.isLowerCase()) it.titlecase() else it.toString() 
                        }
                    }
                Log.d(TAG, "Nome extraído (fallback) da linha ${i + 1}: $capitalized")
                return capitalized
            }
        }
        
        return null
    }

    /**
     * Limpa texto para extração de número.
     */
    private fun cleanTextForNumber(text: String): String {
        // Corrige erros comuns de OCR
        var cleaned = text
            // Corrige "o" (letra O) para "0" (zero) quando está em contexto numérico
            .replace(Regex("""(\d|^|\s)[oO](\d)"""), "$10$2")  // o76 -> 076
            .replace(Regex("""(\d)[oO](\d|/|\s|$)"""), "$10$2")  // 7o -> 70
            .replace(Regex("""(\d)[O](\d)"""), "$10$2")
        
        return cleaned
            .replace("\\s+".toRegex(), " ")
            .trim()
    }

    fun release() {
        textRecognizer.close()
    }
}

