package com.seuapp.pokescanner.ui.scanner

/**
 * Estado da UI do scanner.
 */
data class ScannerUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detectedCardNumber: String? = null,  // Número detectado (sem fazer request)
    val scannedCard: com.seuapp.pokescanner.domain.model.PokemonCard? = null
)
