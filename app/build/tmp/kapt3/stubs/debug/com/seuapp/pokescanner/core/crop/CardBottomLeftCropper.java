package com.seuapp.pokescanner.core.crop;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Cortador de região inferior esquerda da carta.
 *
 * Região de crop onde fica o número da carta (ex: 076/091):
 * - X: 0% até 50% da largura (ajustado para pegar número completo)
 * - Y: 75% até 100% da altura (ajustado para pegar região inferior)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/seuapp/pokescanner/core/crop/CardBottomLeftCropper;", "", "()V", "cropBottomLeftRegion", "Landroid/graphics/Bitmap;", "bitmap", "Companion", "app_debug"})
public final class CardBottomLeftCropper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CardBottomLeftCropper";
    private static final float CROP_LEFT = 0.0F;
    private static final float CROP_RIGHT = 0.5F;
    private static final float CROP_TOP = 0.75F;
    private static final float CROP_BOTTOM = 1.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.seuapp.pokescanner.core.crop.CardBottomLeftCropper.Companion Companion = null;
    
    public CardBottomLeftCropper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap cropBottomLeftRegion(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/seuapp/pokescanner/core/crop/CardBottomLeftCropper$Companion;", "", "()V", "CROP_BOTTOM", "", "CROP_LEFT", "CROP_RIGHT", "CROP_TOP", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}