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
            
            // Extrai número primeiro (formato XXX/XXX)
            val number = extractCardNumber(fullText)
            Log.d(TAG, "Número detectado pelo extractCardNumber: $number")
            
            // Extrai nome (focando em linhas mais abaixo, ignorando texto superior)
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
     * Prioriza formato "XXX/XXX" (número da carta) e ignora "N° XXX" (número do Pokédex).
     */
    private fun extractCardNumber(text: String): String? {
        // Primeiro corrige "o" para "0" em contexto numérico (ex: "o52" -> "052")
        var cleaned = cleanTextForNumber(text)
        
        // Procura TODOS os padrões de número/total no texto (formato XXX/XXX)
        val cardNumberPattern = Regex("""(\d{1,3})\s*[/\\]\s*(\d{1,3})""")
        val allMatches = cardNumberPattern.findAll(cleaned).toList()
        
        if (allMatches.isEmpty()) {
            // Se não encontrou formato XXX/XXX, retorna null (não tenta pegar número solto)
            return null
        }
        
        Log.d(TAG, "Encontrados ${allMatches.size} padrões de número/total no texto")
        
        // Prioriza padrões que estão no final do texto (onde geralmente está o número da carta)
        // e ignora padrões que estão no início (geralmente são número do Pokédex)
        val textLength = cleaned.length
        
        // Procura o padrão mais próximo do final do texto e que tem formato completo (XXX/XXX)
        var bestMatch: MatchResult? = null
        var bestPosition = -1
        
        for (match in allMatches) {
            val matchPosition = match.range.first
            val matchText = match.value
            
            // Ignora padrões que estão logo após "N°" ou "Nº" (são número do Pokédex)
            val beforeMatch = cleaned.substring(0, matchPosition.coerceAtMost(cleaned.length))
            val hasPokedexPrefix = Regex("""[Nn°º]\s*\d+\s*$""").find(beforeMatch) != null
            
            if (hasPokedexPrefix) {
                Log.d(TAG, "Ignorando padrão (tem prefixo Pokédex): $matchText na posição $matchPosition")
                continue
            }
            
            // Prefere padrões que estão nos últimos 50% do texto (onde está o número da carta)
            val isNearEnd = matchPosition > (textLength * 0.5)
            
            // Prioriza padrões que estão mais próximos do final
            if (bestMatch == null || matchPosition > bestPosition) {
                bestMatch = match
                bestPosition = matchPosition
                Log.d(TAG, "Candidato encontrado: $matchText na posição $matchPosition (próximo do final: $isNearEnd)")
            }
        }
        
        // Se não encontrou um bom match, pega o último (mais provável de ser o número da carta)
        val finalMatch = bestMatch ?: allMatches.lastOrNull()
        
        if (finalMatch != null) {
            val number = finalMatch.groupValues[1]
            val total = finalMatch.groupValues.getOrNull(2)
            
            // Só retorna se tiver o formato completo XXX/XXX
            if (total != null && total.isNotBlank()) {
                // Garante que número tem 3 dígitos (052 ao invés de 52)
                val formattedNumber = number.padStart(3, '0')
                val result = "$formattedNumber/$total"
                Log.d(TAG, "✅ Número da carta extraído: $result (posição: ${finalMatch.range}, texto: '${finalMatch.value}')")
                return result
            }
        }
        
        Log.w(TAG, "Nenhum padrão válido de número/total encontrado")
        return null
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
        // Inclui variações com erros de OCR comuns
        val cardTypeKeywords = listOf(
            "estádio", "estadio", "stadium",
            "treinador", "trainer", 
            "apoiador", "supporter", // Tipo de carta Treinador-Apoiador
            "pokémon", "pokemon",
            "basic", "básico", "bậsico", "basico", // Variações de OCR (basic, básico, básico com erro)
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
        
        // Procura nas primeiras 6-10 linhas (onde geralmente está o nome)
        val searchRange = (0 until lines.size.coerceAtMost(10))
        
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
            
            // Pula linhas que são tipo de carta (ex: "Estádio", "BÁSICO", "TREINADOR", "BASIC")
            // Normaliza a linha para remover acentos e permitir comparação mais robusta
            val normalizedLine = lowerLine
                .replace("ậ", "a").replace("á", "a").replace("à", "a").replace("â", "a").replace("ã", "a")
                .replace("é", "e").replace("è", "e").replace("ê", "e")
                .replace("í", "i").replace("ì", "i").replace("î", "i")
                .replace("ó", "o").replace("ò", "o").replace("ô", "o").replace("õ", "o")
                .replace("ú", "u").replace("ù", "u").replace("û", "u")
                .trim()
            
            val isCardType = cardTypeKeywords.any { keyword -> 
                val keywordLower = keyword.lowercase()
                val normalizedKeyword = keywordLower
                    .replace("ậ", "a").replace("á", "a").replace("à", "a").replace("â", "a").replace("ã", "a")
                    .replace("é", "e").replace("è", "e").replace("ê", "e")
                    .replace("í", "i").replace("ì", "i").replace("î", "i")
                    .replace("ó", "o").replace("ò", "o").replace("ô", "o").replace("õ", "o")
                    .replace("ú", "u").replace("ù", "u").replace("û", "u")
                
                // Verifica múltiplas condições para garantir que ignore corretamente
                normalizedLine == normalizedKeyword ||  // Exatamente igual (normalizado)
                normalizedLine == normalizedKeyword.uppercase() ||  // Tudo maiúsculo (normalizado)
                normalizedLine.startsWith(normalizedKeyword + " ") ||  // Começa com a palavra
                normalizedLine.startsWith(normalizedKeyword.uppercase() + " ") ||  // Começa maiúsculo
                // Verifica se a linha contém apenas a palavra-chave (permite pequenas variações de OCR)
                (normalizedLine.contains(normalizedKeyword) && normalizedLine.length <= normalizedKeyword.length + 3)
            }
            if (isCardType) {
                Log.d(TAG, "Ignorando linha (tipo de carta): $line -> normalizado: $normalizedLine")
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
            // Normaliza a linha anterior para comparar (remove acentos de OCR)
            val isAfterCardType = if (i > 0) {
                val prevLine = lines[i - 1].lowercase()
                    .replace("ậ", "a").replace("á", "a").replace("à", "a").replace("â", "a").replace("ã", "a")
                    .replace("é", "e").replace("è", "e").replace("ê", "e")
                    .replace("í", "i").replace("ì", "i").replace("î", "i")
                    .replace("ó", "o").replace("ò", "o").replace("ô", "o").replace("õ", "o")
                    .replace("ú", "u").replace("ù", "u").replace("û", "u")
                
                cardTypeKeywords.any { keyword ->
                    val keywordLower = keyword.lowercase()
                    // Verifica se a linha anterior é exatamente o tipo de carta
                    prevLine == keywordLower || 
                    prevLine.startsWith(keywordLower + " ") || 
                    prevLine == keyword.uppercase() ||
                    // Verifica se contém a palavra-chave e tem tamanho similar (evita falsos positivos)
                    (prevLine.contains(keywordLower) && prevLine.length <= keywordLower.length + 3)
                }
            } else {
                false
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
     * Corrige erros comuns de OCR, especialmente "o" -> "0".
     */
    private fun cleanTextForNumber(text: String): String {
        // Corrige erros comuns de OCR, especialmente "o" -> "0"
        var cleaned = text
            // Prioridade: Corrige padrão completo "o52/132" -> "052/132"
            .replace(Regex("""\b[oO](\d{1,3})\s*[/\\]\s*(\d{1,3})\b"""), "0$1/$2")  // o52/132 -> 052/132
            // Corrige "o" no início de número seguido de barra ou espaço
            .replace(Regex("""(\s|^|[A-Z])[oO](\d{1,3})(\s|/|$)"""), "$10$2$3")  // o52 -> 052, MEGPI o52 -> MEGPI 052
            // Corrige "o" entre dígitos
            .replace(Regex("""(\d)[oO](\d)"""), "$10$2")  // 5o2 -> 502
            // Corrige "o" antes de barra
            .replace(Regex("""(\d|/)[oO](\d)"""), "$10$2")  // /o52 -> /052
        
        return cleaned
            .replace("\\s+".toRegex(), " ")
            .trim()
    }

    fun release() {
        textRecognizer.close()
    }
}

