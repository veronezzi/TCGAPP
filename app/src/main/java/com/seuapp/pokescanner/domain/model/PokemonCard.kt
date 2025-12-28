package com.seuapp.pokescanner.domain.model

/**
 * Modelo de domínio para uma carta Pokémon.
 */
data class PokemonCard(
    val id: String,
    val name: String,
    val number: String,
    val set: CardSet,
    val imageUrl: String?,
    val imageUrlHiRes: String?,
    val prices: CardPrices?
)

/**
 * Informações do set (coleção) da carta.
 */
data class CardSet(
    val id: String,
    val name: String,
    val code: String?
)

/**
 * Preços de mercado da carta.
 */
data class CardPrices(
    val low: Double?,
    val mid: Double?,
    val high: Double?,
    val market: Double?
)

