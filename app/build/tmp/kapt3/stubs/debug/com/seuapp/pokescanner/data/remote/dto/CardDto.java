package com.seuapp.pokescanner.data.remote.dto;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003JQ\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006$"}, d2 = {"Lcom/seuapp/pokescanner/data/remote/dto/CardDto;", "", "id", "", "name", "number", "set", "Lcom/seuapp/pokescanner/data/remote/dto/SetDto;", "images", "Lcom/seuapp/pokescanner/data/remote/dto/ImageDto;", "tcgplayer", "Lcom/seuapp/pokescanner/data/remote/dto/TcgPlayerDto;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/seuapp/pokescanner/data/remote/dto/SetDto;Lcom/seuapp/pokescanner/data/remote/dto/ImageDto;Lcom/seuapp/pokescanner/data/remote/dto/TcgPlayerDto;)V", "getId", "()Ljava/lang/String;", "getImages", "()Lcom/seuapp/pokescanner/data/remote/dto/ImageDto;", "getName", "getNumber", "getSet", "()Lcom/seuapp/pokescanner/data/remote/dto/SetDto;", "getTcgplayer", "()Lcom/seuapp/pokescanner/data/remote/dto/TcgPlayerDto;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class CardDto {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String number = null;
    @org.jetbrains.annotations.Nullable()
    private final com.seuapp.pokescanner.data.remote.dto.SetDto set = null;
    @org.jetbrains.annotations.Nullable()
    private final com.seuapp.pokescanner.data.remote.dto.ImageDto images = null;
    @org.jetbrains.annotations.Nullable()
    private final com.seuapp.pokescanner.data.remote.dto.TcgPlayerDto tcgplayer = null;
    
    public CardDto(@org.jetbrains.annotations.Nullable()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String number, @org.jetbrains.annotations.Nullable()
    com.seuapp.pokescanner.data.remote.dto.SetDto set, @org.jetbrains.annotations.Nullable()
    com.seuapp.pokescanner.data.remote.dto.ImageDto images, @org.jetbrains.annotations.Nullable()
    com.seuapp.pokescanner.data.remote.dto.TcgPlayerDto tcgplayer) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.seuapp.pokescanner.data.remote.dto.SetDto getSet() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.seuapp.pokescanner.data.remote.dto.ImageDto getImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.seuapp.pokescanner.data.remote.dto.TcgPlayerDto getTcgplayer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.seuapp.pokescanner.data.remote.dto.SetDto component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.seuapp.pokescanner.data.remote.dto.ImageDto component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.seuapp.pokescanner.data.remote.dto.TcgPlayerDto component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.seuapp.pokescanner.data.remote.dto.CardDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String number, @org.jetbrains.annotations.Nullable()
    com.seuapp.pokescanner.data.remote.dto.SetDto set, @org.jetbrains.annotations.Nullable()
    com.seuapp.pokescanner.data.remote.dto.ImageDto images, @org.jetbrains.annotations.Nullable()
    com.seuapp.pokescanner.data.remote.dto.TcgPlayerDto tcgplayer) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}