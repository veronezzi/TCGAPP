package com.seuapp.pokescanner.data.repository;

import com.seuapp.pokescanner.data.remote.api.PokemonTcgApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class PokemonCardRepository_Factory implements Factory<PokemonCardRepository> {
  private final Provider<PokemonTcgApi> pokemonTcgApiProvider;

  public PokemonCardRepository_Factory(Provider<PokemonTcgApi> pokemonTcgApiProvider) {
    this.pokemonTcgApiProvider = pokemonTcgApiProvider;
  }

  @Override
  public PokemonCardRepository get() {
    return newInstance(pokemonTcgApiProvider.get());
  }

  public static PokemonCardRepository_Factory create(
      Provider<PokemonTcgApi> pokemonTcgApiProvider) {
    return new PokemonCardRepository_Factory(pokemonTcgApiProvider);
  }

  public static PokemonCardRepository newInstance(PokemonTcgApi pokemonTcgApi) {
    return new PokemonCardRepository(pokemonTcgApi);
  }
}
