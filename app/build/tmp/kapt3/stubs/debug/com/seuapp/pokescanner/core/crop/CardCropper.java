package com.seuapp.pokescanner.core.crop;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Cortador de carta completa.
 *
 * Retorna a carta inteira para extrair tanto nome quanto número.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/seuapp/pokescanner/core/crop/CardCropper;", "", "()V", "cropCard", "Landroid/graphics/Bitmap;", "fullImage", "Companion", "app_debug"})
public final class CardCropper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CardCropper";
    @org.jetbrains.annotations.NotNull()
    public static final com.seuapp.pokescanner.core.crop.CardCropper.Companion Companion = null;
    
    public CardCropper() {
        super();
    }
    
    /**
     * Detecta e corta a região da carta na imagem.
     *
     * Por enquanto, retorna a imagem inteira.
     * Em versões futuras, pode implementar detecção automática da carta.
     */
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap cropCard(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap fullImage) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/seuapp/pokescanner/core/crop/CardCropper$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}