package com.seuapp.pokescanner.data.repository

import android.util.Log
import com.seuapp.pokescanner.data.remote.api.PokemonTcgApi
import com.seuapp.pokescanner.domain.model.CardPrices
import com.seuapp.pokescanner.domain.model.CardSet
import com.seuapp.pokescanner.domain.model.PokemonCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repositório para buscar cartas Pokémon usando a API do Pokémon TCG.
 */
class PokemonCardRepository @Inject constructor(
    private val pokemonTcgApi: PokemonTcgApi
) {
    companion object {
        private const val TAG = "PokemonCardRepository"
        private const val API_BASE_URL = "https://api.pokemontcg.io/v2/"
    }

    /**
     * Busca uma carta pelo número.
     * Tenta diferentes formatos de busca se não encontrar.
     */
    suspend fun findCardByNumber(cardNumber: String): PokemonCard? {
        return withContext(Dispatchers.IO) {
            try {
                // Lista de formatos para tentar buscar
                val searchFormats = generateSearchFormats(cardNumber)
                
                Log.d(TAG, "Buscando carta com número original: $cardNumber")
                Log.d(TAG, "Formatos de busca: $searchFormats")
                
                for (searchFormat in searchFormats) {
                    try {
                        Log.d(TAG, "Tentando buscar com formato: number:$searchFormat")
                        
                        val response = pokemonTcgApi.searchCards(
                            query = "number:$searchFormat",
                            pageSize = 10
                        )
                        
                        Log.d(TAG, "Resposta da API: ${response.data?.size ?: 0} cartas encontradas")
                        
                        if (response.data != null && response.data.isNotEmpty()) {
                            val cardDto = response.data.first()
                            val card = mapDtoToDomain(cardDto)
                            Log.d(TAG, "✅ Carta encontrada: ${card.name} (${card.number}) - Set: ${card.set.name}")
                            return@withContext card
                        }
                    } catch (e: Exception) {
                        Log.w(TAG, "Erro ao buscar com formato '$searchFormat': ${e.message}", e)
                        // Continua tentando outros formatos
                    }
                }
                
                Log.w(TAG, "Nenhuma carta encontrada para número: $cardNumber")
                null
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao buscar carta por número: $cardNumber", e)
                null
            }
        }
    }
    
    /**
     * Gera diferentes formatos de busca para o número da carta.
     * Exemplos:
     * - "025/198" -> ["025/198", "25/198", "25"]
     * - "025" -> ["025", "25"]
     */
    private fun generateSearchFormats(cardNumber: String): List<String> {
        val formats = mutableListOf<String>()
        val trimmed = cardNumber.trim()
        
        if (trimmed.contains("/")) {
            val parts = trimmed.split("/")
            if (parts.size == 2) {
                val number = parts[0].trim()
                val total = parts[1].trim()
                
                // Formato completo: "025/198"
                formats.add("$number/$total")
                
                // Formato sem zeros à esquerda: "25/198"
                val numberInt = number.toIntOrNull()
                if (numberInt != null) {
                    formats.add("$numberInt/$total")
                }
                
                // Apenas o número: "25"
                if (numberInt != null) {
                    formats.add(numberInt.toString())
                    // Com zeros à esquerda: "025"
                    formats.add(number.padStart(3, '0'))
                }
            }
        } else {
            // Formato simples: "025" ou "25"
            formats.add(trimmed)
            
            val numberInt = trimmed.toIntOrNull()
            if (numberInt != null) {
                // Sem zeros à esquerda: "25"
                formats.add(numberInt.toString())
                // Com zeros à esquerda: "025"
                formats.add(trimmed.padStart(3, '0'))
            }
        }
        
        // Remove duplicatas mantendo a ordem
        return formats.distinct()
    }

    /**
     * Busca uma carta pelo ID.
     */
    suspend fun findCardById(cardId: String): PokemonCard? {
        return withContext(Dispatchers.IO) {
            try {
                val cardDto = pokemonTcgApi.getCardById(cardId)
                mapDtoToDomain(cardDto)
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao buscar carta por ID: $cardId", e)
                null
            }
        }
    }

    private fun normalizeNumberForSearch(cardNumber: String): String {
        if (cardNumber.contains("/")) {
            val parts = cardNumber.split("/")
            if (parts.size == 2) {
                val number = parts[0].trim().toIntOrNull()?.toString() ?: parts[0].trim()
                val total = parts[1].trim()
                return "$number/$total"
            }
        }
        return cardNumber.trim().toIntOrNull()?.toString() ?: cardNumber.trim()
    }

    private fun mapDtoToDomain(dto: com.seuapp.pokescanner.data.remote.dto.CardDto): PokemonCard {
        val prices = dto.tcgplayer?.prices?.let {
            CardPrices(
                low = it.low,
                mid = it.mid,
                high = it.high,
                market = it.market
            )
        }
        
        return PokemonCard(
            id = dto.id ?: "",
            name = dto.name ?: "Nome desconhecido",
            number = dto.number ?: "",
            set = CardSet(
                id = dto.set?.id ?: "",
                name = dto.set?.name ?: "Coleção desconhecida",
                code = dto.set?.code
            ),
            imageUrl = dto.images?.small,
            imageUrlHiRes = dto.images?.large,
            prices = prices
        )
    }
}
