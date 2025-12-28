package com.seuapp.pokescanner.ui.collection;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CollectionViewModel_Factory implements Factory<CollectionViewModel> {
  @Override
  public CollectionViewModel get() {
    return newInstance();
  }

  public static CollectionViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CollectionViewModel newInstance() {
    return new CollectionViewModel();
  }

  private static final class InstanceHolder {
    private static final CollectionViewModel_Factory INSTANCE = new CollectionViewModel_Factory();
  }
}
