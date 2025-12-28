package com.seuapp.pokescanner.data.remote.api

import com.seuapp.pokescanner.data.remote.dto.CardDto
import com.seuapp.pokescanner.data.remote.dto.CardsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API Retrofit para Pokemon TCG API (https://pokemontcg.io/).
 */
interface PokemonTcgApi {
    @GET("cards")
    suspend fun getCards(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 250
    ): CardsResponseDto

    @GET("cards/{id}")
    suspend fun getCardById(@Path("id") id: String): CardDto

    @GET("cards")
    suspend fun searchCards(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): CardsResponseDto
}
