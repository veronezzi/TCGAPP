package com.seuapp.pokescanner.data.remote.api;

import com.seuapp.pokescanner.data.remote.dto.CardDto;
import com.seuapp.pokescanner.data.remote.dto.CardsResponseDto;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API Retrofit para Pokemon TCG API (https://pokemontcg.io/).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\fJ,\u0010\r\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\u00052\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/seuapp/pokescanner/data/remote/api/PokemonTcgApi;", "", "getCardById", "Lcom/seuapp/pokescanner/data/remote/dto/CardDto;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCards", "Lcom/seuapp/pokescanner/data/remote/dto/CardsResponseDto;", "page", "", "pageSize", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCards", "query", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PokemonTcgApi {
    
    @retrofit2.http.GET(value = "cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCards(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "pageSize")
    int pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seuapp.pokescanner.data.remote.dto.CardsResponseDto> $completion);
    
    @retrofit2.http.GET(value = "cards/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCardById(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seuapp.pokescanner.data.remote.dto.CardDto> $completion);
    
    @retrofit2.http.GET(value = "cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchCards(@retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "pageSize")
    int pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seuapp.pokescanner.data.remote.dto.CardsResponseDto> $completion);
    
    /**
     * API Retrofit para Pokemon TCG API (https://pokemontcg.io/).
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}