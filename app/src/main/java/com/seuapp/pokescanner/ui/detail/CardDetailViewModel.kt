package com.seuapp.pokescanner.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuapp.pokescanner.data.repository.PokemonCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para a tela de detalhes da carta.
 */
@HiltViewModel
class CardDetailViewModel @Inject constructor(
    private val pokemonCardRepository: PokemonCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardDetailUiState())
    val uiState: StateFlow<CardDetailUiState> = _uiState.asStateFlow()

    fun loadCard(cardId: String) {
        if (cardId.isBlank()) {
            _uiState.value = CardDetailUiState(
                isLoading = false,
                error = "ID da carta inválido"
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = CardDetailUiState(isLoading = true)
            
            try {
                val card = pokemonCardRepository.findCardById(cardId)
                _uiState.value = CardDetailUiState(
                    isLoading = false,
                    card = card,
                    error = if (card == null) "Carta não encontrada" else null
                )
            } catch (e: Exception) {
                _uiState.value = CardDetailUiState(
                    isLoading = false,
                    error = "Erro ao carregar carta: ${e.message}"
                )
            }
        }
    }
}

