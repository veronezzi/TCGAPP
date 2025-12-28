package com.seuapp.pokescanner.ui.detail;

import com.seuapp.pokescanner.data.repository.PokemonCardRepository;
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
public final class CardDetailViewModel_Factory implements Factory<CardDetailViewModel> {
  private final Provider<PokemonCardRepository> pokemonCardRepositoryProvider;

  public CardDetailViewModel_Factory(
      Provider<PokemonCardRepository> pokemonCardRepositoryProvider) {
    this.pokemonCardRepositoryProvider = pokemonCardRepositoryProvider;
  }

  @Override
  public CardDetailViewModel get() {
    return newInstance(pokemonCardRepositoryProvider.get());
  }

  public static CardDetailViewModel_Factory create(
      Provider<PokemonCardRepository> pokemonCardRepositoryProvider) {
    return new CardDetailViewModel_Factory(pokemonCardRepositoryProvider);
  }

  public static CardDetailViewModel newInstance(PokemonCardRepository pokemonCardRepository) {
    return new CardDetailViewModel(pokemonCardRepository);
  }
}
