package com.seuapp.pokescanner.ui.detail

import com.seuapp.pokescanner.domain.model.PokemonCard

/**
 * Estado da UI de detalhes da carta.
 */
data class CardDetailUiState(
    val isLoading: Boolean = false,
    val card: PokemonCard? = null,
    val error: String? = null
)

