package com.seuapp.pokescanner.core.ocr;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

/**
 * OCR especializado para extrair número da carta do canto inferior esquerdo.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/seuapp/pokescanner/core/ocr/CardNumberOcr;", "", "()V", "textRecognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "cleanText", "", "text", "extractCardNumber", "croppedBitmap", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractNumberWithRegex", "release", "", "Companion", "app_debug"})
public final class CardNumberOcr {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CardNumberOcr";
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex CARD_NUMBER_REGEX = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.mlkit.vision.text.TextRecognizer textRecognizer = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.seuapp.pokescanner.core.ocr.CardNumberOcr.Companion Companion = null;
    
    public CardNumberOcr() {
        super();
    }
    
    /**
     * Extrai o número da carta do bitmap recortado.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object extractCardNumber(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap croppedBitmap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.String cleanText(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String extractNumberWithRegex(java.lang.String text) {
        return null;
    }
    
    public final void release() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/seuapp/pokescanner/core/ocr/CardNumberOcr$Companion;", "", "()V", "CARD_NUMBER_REGEX", "Lkotlin/text/Regex;", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}