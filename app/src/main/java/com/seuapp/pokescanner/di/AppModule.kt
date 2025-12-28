package com.seuapp.pokescanner.di

import com.seuapp.pokescanner.data.remote.api.PokemonTcgApi
import com.seuapp.pokescanner.data.repository.PokemonCardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Módulo Hilt para injeção de dependências.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val POKEMON_TCG_API_BASE_URL = "https://api.pokemontcg.io/v2/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(POKEMON_TCG_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonTcgApi(retrofit: Retrofit): PokemonTcgApi {
        return retrofit.create(PokemonTcgApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonCardRepository(pokemonTcgApi: PokemonTcgApi): PokemonCardRepository {
        return PokemonCardRepository(pokemonTcgApi)
    }
}
