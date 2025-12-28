package com.seuapp.pokescanner.ui.scanner;

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
public final class ScannerViewModel_Factory implements Factory<ScannerViewModel> {
  private final Provider<PokemonCardRepository> pokemonCardRepositoryProvider;

  public ScannerViewModel_Factory(Provider<PokemonCardRepository> pokemonCardRepositoryProvider) {
    this.pokemonCardRepositoryProvider = pokemonCardRepositoryProvider;
  }

  @Override
  public ScannerViewModel get() {
    return newInstance(pokemonCardRepositoryProvider.get());
  }

  public static ScannerViewModel_Factory create(
      Provider<PokemonCardRepository> pokemonCardRepositoryProvider) {
    return new ScannerViewModel_Factory(pokemonCardRepositoryProvider);
  }

  public static ScannerViewModel newInstance(PokemonCardRepository pokemonCardRepository) {
    return new ScannerViewModel(pokemonCardRepository);
  }
}
