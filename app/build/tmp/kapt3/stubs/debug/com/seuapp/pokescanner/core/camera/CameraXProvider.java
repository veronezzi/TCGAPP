package com.seuapp.pokescanner.core.camera;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executor;

/**
 * Provedor de câmera usando CameraX.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0017R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/seuapp/pokescanner/core/camera/CameraXProvider;", "", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "executor", "Ljava/util/concurrent/Executor;", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Ljava/util/concurrent/Executor;)V", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "preview", "Landroidx/camera/core/Preview;", "captureFrame", "Landroid/graphics/Bitmap;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCameraProvider", "imageProxyToBitmap", "imageProxy", "Landroidx/camera/core/ImageProxy;", "initialize", "", "previewView", "Landroidx/camera/view/PreviewView;", "(Landroidx/camera/view/PreviewView;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "release", "app_debug"})
public final class CameraXProvider {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LifecycleOwner lifecycleOwner = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.Executor executor = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.lifecycle.ProcessCameraProvider cameraProvider;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.core.ImageCapture imageCapture;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.core.Preview preview;
    
    public CameraXProvider(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.Executor executor) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull()
    androidx.camera.view.PreviewView previewView, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object captureFrame(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.graphics.Bitmap> $completion) {
        return null;
    }
    
    private final android.graphics.Bitmap imageProxyToBitmap(androidx.camera.core.ImageProxy imageProxy) {
        return null;
    }
    
    private final java.lang.Object getCameraProvider(kotlin.coroutines.Continuation<? super androidx.camera.lifecycle.ProcessCameraProvider> $completion) {
        return null;
    }
    
    public final void release() {
    }
}