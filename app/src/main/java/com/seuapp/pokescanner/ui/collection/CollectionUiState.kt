package com.seuapp.pokescanner.ui.collection

import com.seuapp.pokescanner.domain.model.PokemonCard

/**
 * Estado da UI da coleção.
 */
data class CollectionUiState(
    val cards: List<PokemonCard> = emptyList(),
    val totalValue: String = "$0.00"
)

