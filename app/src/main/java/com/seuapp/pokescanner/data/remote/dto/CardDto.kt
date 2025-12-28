package com.seuapp.pokescanner.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para resposta da API Pokemon TCG.
 */
data class CardsResponseDto(
    val data: List<CardDto>?,
    val page: Int?,
    val pageSize: Int?,
    val count: Int?,
    val totalCount: Int?
)

data class CardDto(
    val id: String?,
    val name: String?,
    val number: String?,
    val set: SetDto?,
    val images: ImageDto?,
    val tcgplayer: TcgPlayerDto?
)

data class SetDto(
    val id: String?,
    val name: String?,
    val code: String?
)

data class ImageDto(
    val small: String?,
    val large: String?
)

data class TcgPlayerDto(
    val prices: PricesDto?
)

data class PricesDto(
    val low: Double?,
    val mid: Double?,
    val high: Double?,
    val market: Double?
)
