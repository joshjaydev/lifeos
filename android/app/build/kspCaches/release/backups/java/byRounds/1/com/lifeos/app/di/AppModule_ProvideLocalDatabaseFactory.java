package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.data.local.LocalDatabase;
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
public final class AppModule_ProvideLocalDatabaseFactory implements Factory<LocalDatabase> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideLocalDatabaseFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LocalDatabase get() {
    return provideLocalDatabase(databaseProvider.get());
  }

  public static AppModule_ProvideLocalDatabaseFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideLocalDatabaseFactory(databaseProvider);
  }

  public static LocalDatabase provideLocalDatabase(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLocalDatabase(database));
  }
}
