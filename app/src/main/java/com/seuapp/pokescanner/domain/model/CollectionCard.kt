package com.seuapp.pokescanner.domain.model

/**
 * Carta na coleção do usuário.
 */
data class CollectionCard(
    val id: String,
    val cardId: String,
    val card: PokemonCard,
    val quantity: Int,
    val condition: String, // "Near Mint", "Lightly Played", etc
    val addedDate: Long
)

