package com.seuapp.pokescanner.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para resposta da API Pokemon TCG.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJP\u0010\u0018\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0011\u0010\fR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\f\u00a8\u0006 "}, d2 = {"Lcom/seuapp/pokescanner/data/remote/dto/CardsResponseDto;", "", "data", "", "Lcom/seuapp/pokescanner/data/remote/dto/CardDto;", "page", "", "pageSize", "count", "totalCount", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getData", "()Ljava/util/List;", "getPage", "getPageSize", "getTotalCount", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/seuapp/pokescanner/data/remote/dto/CardsResponseDto;", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class CardsResponseDto {
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<com.seuapp.pokescanner.data.remote.dto.CardDto> data = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer page = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer pageSize = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer count = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer totalCount = null;
    
    public CardsResponseDto(@org.jetbrains.annotations.Nullable()
    java.util.List<com.seuapp.pokescanner.data.remote.dto.CardDto> data, @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pageSize, @org.jetbrains.annotations.Nullable()
    java.lang.Integer count, @org.jetbrains.annotations.Nullable()
    java.lang.Integer totalCount) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.seuapp.pokescanner.data.remote.dto.CardDto> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPageSize() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTotalCount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.seuapp.pokescanner.data.remote.dto.CardDto> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.seuapp.pokescanner.data.remote.dto.CardsResponseDto copy(@org.jetbrains.annotations.Nullable()
    java.util.List<com.seuapp.pokescanner.data.remote.dto.CardDto> data, @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pageSize, @org.jetbrains.annotations.Nullable()
    java.lang.Integer count, @org.jetbrains.annotations.Nullable()
    java.lang.Integer totalCount) {
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