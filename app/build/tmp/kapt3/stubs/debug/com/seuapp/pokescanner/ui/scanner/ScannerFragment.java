package com.seuapp.pokescanner.ui.scanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.seuapp.pokescanner.R;
import com.seuapp.pokescanner.databinding.FragmentScannerBinding;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Fragment para escanear cartas Pokémon.
 *
 * Funcionalidades:
 * - Preview da câmera
 * - Botão de captura
 * - Processamento OCR
 * - Navegação para detalhes da carta
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0013H\u0016J\u001a\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\nH\u0002J\b\u0010\'\u001a\u00020\u0013H\u0002J\b\u0010(\u001a\u00020\u0013H\u0002J\u0010\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\nH\u0002J\u001a\u0010+\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\n2\b\u0010,\u001a\u0004\u0018\u00010\nH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006-"}, d2 = {"Lcom/seuapp/pokescanner/ui/scanner/ScannerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/seuapp/pokescanner/databinding/FragmentScannerBinding;", "binding", "getBinding", "()Lcom/seuapp/pokescanner/databinding/FragmentScannerBinding;", "cameraPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "viewModel", "Lcom/seuapp/pokescanner/ui/scanner/ScannerViewModel;", "getViewModel", "()Lcom/seuapp/pokescanner/ui/scanner/ScannerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkCameraPermission", "", "handleUiState", "state", "Lcom/seuapp/pokescanner/ui/scanner/ScannerUiState;", "navigateToCardDetail", "card", "Lcom/seuapp/pokescanner/domain/model/PokemonCard;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "openLigaPokemon", "cardNumber", "setupClickListeners", "setupObservers", "showCardNumberDetectedDialog", "cardInfo", "showSearchOptionsDialog", "cardName", "app_debug"})
public final class ScannerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.seuapp.pokescanner.databinding.FragmentScannerBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> cameraPermissionLauncher = null;
    
    public ScannerFragment() {
        super();
    }
    
    private final com.seuapp.pokescanner.databinding.FragmentScannerBinding getBinding() {
        return null;
    }
    
    private final com.seuapp.pokescanner.ui.scanner.ScannerViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupObservers() {
    }
    
    private final void handleUiState(com.seuapp.pokescanner.ui.scanner.ScannerUiState state) {
    }
    
    private final void showCardNumberDetectedDialog(java.lang.String cardInfo) {
    }
    
    private final void showSearchOptionsDialog(java.lang.String cardNumber, java.lang.String cardName) {
    }
    
    private final void openLigaPokemon(java.lang.String cardNumber) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void checkCameraPermission() {
    }
    
    private final void navigateToCardDetail(com.seuapp.pokescanner.domain.model.PokemonCard card) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}