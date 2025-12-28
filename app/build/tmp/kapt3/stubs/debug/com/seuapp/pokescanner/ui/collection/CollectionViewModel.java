package com.seuapp.pokescanner.ui.collection;

import androidx.lifecycle.ViewModel;
import com.seuapp.pokescanner.domain.model.PokemonCard;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * ViewModel para a tela de coleção.
 *
 * TODO: Integrar com Room database quando implementado.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/seuapp/pokescanner/ui/collection/CollectionViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/seuapp/pokescanner/ui/collection/CollectionUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadCollection", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CollectionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.seuapp.pokescanner.ui.collection.CollectionUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.seuapp.pokescanner.ui.collection.CollectionUiState> uiState = null;
    
    @javax.inject.Inject()
    public CollectionViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.seuapp.pokescanner.ui.collection.CollectionUiState> getUiState() {
        return null;
    }
    
    private final void loadCollection() {
    }
}