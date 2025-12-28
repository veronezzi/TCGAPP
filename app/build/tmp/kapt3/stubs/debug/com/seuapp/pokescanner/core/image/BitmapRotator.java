package com.seuapp.pokescanner.core.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

/**
 * Rotaciona bitmap para orientação correta.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/seuapp/pokescanner/core/image/BitmapRotator;", "", "()V", "TAG", "", "rotateForPortrait", "Landroid/graphics/Bitmap;", "bitmap", "rotateIfNeeded", "rotationDegrees", "", "app_debug"})
public final class BitmapRotator {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "BitmapRotator";
    @org.jetbrains.annotations.NotNull()
    public static final com.seuapp.pokescanner.core.image.BitmapRotator INSTANCE = null;
    
    private BitmapRotator() {
        super();
    }
    
    /**
     * Rotaciona a imagem para a orientação correta.
     * CameraX pode retornar a imagem rotacionada.
     */
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap rotateIfNeeded(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, int rotationDegrees) {
        return null;
    }
    
    /**
     * Para câmera traseira em modo retrato, pode precisar rotacionar.
     * Verifica as dimensões para determinar se precisa rotacionar.
     */
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap rotateForPortrait(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
}