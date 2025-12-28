package com.seuapp.pokescanner.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuapp.pokescanner.domain.model.PokemonCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para a tela de coleção.
 * 
 * TODO: Integrar com Room database quando implementado.
 */
@HiltViewModel
class CollectionViewModel @Inject constructor(
    // TODO: Injetar CollectionRepository quando implementado
) : ViewModel() {

    private val _uiState = MutableStateFlow(CollectionUiState())
    val uiState: StateFlow<CollectionUiState> = _uiState.asStateFlow()

    init {
        loadCollection()
    }

    private fun loadCollection() {
        viewModelScope.launch {
            // TODO: Buscar cartas da coleção do Room database
            _uiState.value = CollectionUiState(
                cards = emptyList(),
                totalValue = "$0.00"
            )
        }
    }
}

