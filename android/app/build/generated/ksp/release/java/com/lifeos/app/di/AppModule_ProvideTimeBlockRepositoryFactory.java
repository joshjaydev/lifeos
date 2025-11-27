package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.TimeBlockRepository;
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
public final class AppModule_ProvideTimeBlockRepositoryFactory implements Factory<TimeBlockRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvideTimeBlockRepositoryFactory(
      Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public TimeBlockRepository get() {
    return provideTimeBlockRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvideTimeBlockRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvideTimeBlockRepositoryFactory(localDatabaseProvider);
  }

  public static TimeBlockRepository provideTimeBlockRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTimeBlockRepository(localDatabase));
  }
}
