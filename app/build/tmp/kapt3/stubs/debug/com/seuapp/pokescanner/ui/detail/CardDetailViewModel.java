package com.seuapp.pokescanner.ui.detail;

import androidx.lifecycle.ViewModel;
import com.seuapp.pokescanner.data.repository.PokemonCardRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * ViewModel para a tela de detalhes da carta.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/seuapp/pokescanner/ui/detail/CardDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "pokemonCardRepository", "Lcom/seuapp/pokescanner/data/repository/PokemonCardRepository;", "(Lcom/seuapp/pokescanner/data/repository/PokemonCardRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/seuapp/pokescanner/ui/detail/CardDetailUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadCard", "", "cardId", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CardDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.seuapp.pokescanner.data.repository.PokemonCardRepository pokemonCardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.seuapp.pokescanner.ui.detail.CardDetailUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.seuapp.pokescanner.ui.detail.CardDetailUiState> uiState = null;
    
    @javax.inject.Inject()
    public CardDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.seuapp.pokescanner.data.repository.PokemonCardRepository pokemonCardRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.seuapp.pokescanner.ui.detail.CardDetailUiState> getUiState() {
        return null;
    }
    
    public final void loadCard(@org.jetbrains.annotations.NotNull()
    java.lang.String cardId) {
    }
}