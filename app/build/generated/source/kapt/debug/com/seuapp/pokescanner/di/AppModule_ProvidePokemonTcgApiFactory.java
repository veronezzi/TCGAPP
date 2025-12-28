package com.seuapp.pokescanner.di;

import com.seuapp.pokescanner.data.remote.api.PokemonTcgApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class AppModule_ProvidePokemonTcgApiFactory implements Factory<PokemonTcgApi> {
  private final Provider<Retrofit> retrofitProvider;

  public AppModule_ProvidePokemonTcgApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PokemonTcgApi get() {
    return providePokemonTcgApi(retrofitProvider.get());
  }

  public static AppModule_ProvidePokemonTcgApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new AppModule_ProvidePokemonTcgApiFactory(retrofitProvider);
  }

  public static PokemonTcgApi providePokemonTcgApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePokemonTcgApi(retrofit));
  }
}
