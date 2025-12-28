package com.seuapp.pokescanner.di;

import com.seuapp.pokescanner.data.remote.api.PokemonTcgApi;
import com.seuapp.pokescanner.data.repository.PokemonCardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvidePokemonCardRepositoryFactory implements Factory<PokemonCardRepository> {
  private final Provider<PokemonTcgApi> pokemonTcgApiProvider;

  public AppModule_ProvidePokemonCardRepositoryFactory(
      Provider<PokemonTcgApi> pokemonTcgApiProvider) {
    this.pokemonTcgApiProvider = pokemonTcgApiProvider;
  }

  @Override
  public PokemonCardRepository get() {
    return providePokemonCardRepository(pokemonTcgApiProvider.get());
  }

  public static AppModule_ProvidePokemonCardRepositoryFactory create(
      Provider<PokemonTcgApi> pokemonTcgApiProvider) {
    return new AppModule_ProvidePokemonCardRepositoryFactory(pokemonTcgApiProvider);
  }

  public static PokemonCardRepository providePokemonCardRepository(PokemonTcgApi pokemonTcgApi) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePokemonCardRepository(pokemonTcgApi));
  }
}
