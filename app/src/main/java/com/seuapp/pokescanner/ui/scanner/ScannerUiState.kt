package com.seuapp.pokescanner.ui.scanner

/**
 * Estado da UI do scanner.
 */
data class ScannerUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detectedCardNumber: String? = null,  // Número detectado (formato: "Nome 076/091" ou apenas "076/091")
    val detectedCardName: String? = null,    // Nome da carta detectado (ex: "Artazon")
    val detectedCardNumberOnly: String? = null,  // Apenas o número (ex: "076/091")
    val scannedCard: com.seuapp.pokescanner.domain.model.PokemonCard? = null
)
