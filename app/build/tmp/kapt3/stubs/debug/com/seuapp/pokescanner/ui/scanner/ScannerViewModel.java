package com.seuapp.pokescanner.ui.scanner;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import com.seuapp.pokescanner.core.camera.CameraXProvider;
import com.seuapp.pokescanner.core.crop.CardCropper;
import com.seuapp.pokescanner.core.image.BitmapRotator;
import com.seuapp.pokescanner.core.image.ImagePreprocessor;
import com.seuapp.pokescanner.core.ocr.CardInfoOcr;
import com.seuapp.pokescanner.data.repository.PokemonCardRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import java.util.concurrent.Executors;
import javax.inject.Inject;

/**
 * ViewModel para a tela de scanner.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u0018J\u0016\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u0018J\u0006\u0010!\u001a\u00020\u0018R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/seuapp/pokescanner/ui/scanner/ScannerViewModel;", "Landroidx/lifecycle/ViewModel;", "pokemonCardRepository", "Lcom/seuapp/pokescanner/data/repository/PokemonCardRepository;", "(Lcom/seuapp/pokescanner/data/repository/PokemonCardRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/seuapp/pokescanner/ui/scanner/ScannerUiState;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "cameraProvider", "Lcom/seuapp/pokescanner/core/camera/CameraXProvider;", "cardCropper", "Lcom/seuapp/pokescanner/core/crop/CardCropper;", "cardInfoOcr", "Lcom/seuapp/pokescanner/core/ocr/CardInfoOcr;", "imagePreprocessor", "Lcom/seuapp/pokescanner/core/image/ImagePreprocessor;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearDetectedCardNumber", "", "clearError", "clearScannedCard", "initializeCamera", "previewView", "Landroidx/camera/view/PreviewView;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "releaseCamera", "scanCard", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ScannerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.seuapp.pokescanner.data.repository.PokemonCardRepository pokemonCardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.seuapp.pokescanner.ui.scanner.ScannerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.seuapp.pokescanner.ui.scanner.ScannerUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.seuapp.pokescanner.core.camera.CameraXProvider cameraProvider;
    private final java.util.concurrent.ExecutorService cameraExecutor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seuapp.pokescanner.core.crop.CardCropper cardCropper = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seuapp.pokescanner.core.image.ImagePreprocessor imagePreprocessor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seuapp.pokescanner.core.ocr.CardInfoOcr cardInfoOcr = null;
    
    @javax.inject.Inject()
    public ScannerViewModel(@org.jetbrains.annotations.NotNull()
    com.seuapp.pokescanner.data.repository.PokemonCardRepository pokemonCardRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.seuapp.pokescanner.ui.scanner.ScannerUiState> getUiState() {
        return null;
    }
    
    public final void initializeCamera(@org.jetbrains.annotations.NotNull()
    androidx.camera.view.PreviewView previewView, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner) {
    }
    
    public final void scanCard() {
    }
    
    public final void clearDetectedCardNumber() {
    }
    
    public final void clearScannedCard() {
    }
    
    public final void clearError() {
    }
    
    public final void releaseCamera() {
    }
}